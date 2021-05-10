package com.kevinproject.backtienda.repository;

import com.kevinproject.backtienda.model.producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface productoRep extends JpaRepository<producto,Integer> {

}
