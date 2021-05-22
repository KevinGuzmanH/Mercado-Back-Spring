package com.kevinproject.backtienda.security.SecurityService;

import com.kevinproject.backtienda.security.SecurityEntity.Rol;
import com.kevinproject.backtienda.security.SecurityEnums.RolNombre;
import com.kevinproject.backtienda.security.SecurityRepository.RolRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
@Transactional
public class RolServ {
    @Autowired
    RolRep rolRep;

    public Optional<Rol> findByRolNombre(RolNombre rolNombre){
        return rolRep.findByRolNombre(rolNombre);
    }
}
