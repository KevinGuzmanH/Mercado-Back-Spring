package com.kevinproject.backtienda.security.SecurityService;

import com.kevinproject.backtienda.security.SecurityEntity.Usuario;
import com.kevinproject.backtienda.security.SecurityRepository.UsuarioRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServ  {
    @Autowired
    UsuarioRep usuarioRep;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRep.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRep.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return usuarioRep.existsByEmail(email);
    }

    public void save(Usuario usuario){
        usuarioRep.save(usuario);
    }
}
