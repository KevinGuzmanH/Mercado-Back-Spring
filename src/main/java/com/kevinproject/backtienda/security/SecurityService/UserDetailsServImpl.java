package com.kevinproject.backtienda.security.SecurityService;

import com.kevinproject.backtienda.security.SecurityEntity.MainUser;
import com.kevinproject.backtienda.security.SecurityEntity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServImpl implements UserDetailsService {
    @Autowired
    UsuarioServ usuarioServ;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioServ.getByNombreUsuario(nombreUsuario).get();
        return MainUser.create(usuario);
    }
}
