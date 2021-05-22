package com.kevinproject.backtienda.security.SecurityController;

import com.kevinproject.backtienda.security.SecurityDto.JwtDto;
import com.kevinproject.backtienda.security.SecurityDto.LoginUsuario;
import com.kevinproject.backtienda.security.SecurityDto.NuevoUsuario;
import com.kevinproject.backtienda.security.SecurityEntity.Rol;
import com.kevinproject.backtienda.security.SecurityEntity.Usuario;
import com.kevinproject.backtienda.security.SecurityEnums.RolNombre;
import com.kevinproject.backtienda.security.SecurityJWT.JwtProvider;
import com.kevinproject.backtienda.security.SecurityService.RolServ;
import com.kevinproject.backtienda.security.SecurityService.UsuarioServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioServ usuarioServ;
    @Autowired
    RolServ rolServ;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsario, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity("Datos Invalidos Reintente Por Favor", HttpStatus.BAD_REQUEST);
        }
        if (usuarioServ.existsByNombreUsuario(nuevoUsario.getNombreUsuario())) {
            return new ResponseEntity("Ese Nombre Ya Esta En Uso", HttpStatus.BAD_REQUEST);
        }
        if (usuarioServ.existsByEmail(nuevoUsario.getEmail())) {
            return new ResponseEntity("Ese Email Ya Esta En Uso", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario= new Usuario(nuevoUsario.getNombre(),nuevoUsario.getNombreUsuario(),nuevoUsario.getEmail(),passwordEncoder.encode(nuevoUsario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolServ.findByRolNombre(RolNombre.ROLE_USER).get());
        if (nuevoUsario.getRoles().contains("admin")){
            roles.add(rolServ.findByRolNombre(RolNombre.ROLE_ADMIN).get());}
        usuario.setRoles(roles);
        usuarioServ.save(usuario);
        return new ResponseEntity("Usuario Guardado",HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity("campos mal puestos", HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generarTkn(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

}
