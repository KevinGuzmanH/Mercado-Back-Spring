package com.kevinproject.backtienda.repository;

import com.kevinproject.backtienda.model.producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface productoRep extends JpaRepository<producto,Integer> {

    List<producto> findByOfertaIsTrue();
}
