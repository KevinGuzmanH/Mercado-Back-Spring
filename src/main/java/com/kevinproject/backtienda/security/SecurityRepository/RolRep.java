package com.kevinproject.backtienda.security.SecurityRepository;

import com.kevinproject.backtienda.security.SecurityEntity.Rol;
import com.kevinproject.backtienda.security.SecurityEnums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRep extends JpaRepository<Rol,Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
