package com.kevinproject.backtienda.security.SecurityJWT;

import com.kevinproject.backtienda.security.SecurityEntity.MainUser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generarTkn(Authentication authentication){
        MainUser mainUser = (MainUser) authentication.getPrincipal();
        return Jwts.builder().setSubject(mainUser.getUsername()).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.ES512,secret).compact();
    }

    public String getnombreUsuarioFromTkn(String token){
       return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validarToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("Token Mal Formado");
        }catch (UnsupportedJwtException e){
            logger.error("Token No Soportado");
        }catch (ExpiredJwtException e){
            logger.error("Token Expirado");
        }catch (IllegalArgumentException e){
            logger.error("Token Vacio");
        }catch (SignatureException e){
            logger.error("Error en la Firma del Token");
        }
        return false;
    }
}
