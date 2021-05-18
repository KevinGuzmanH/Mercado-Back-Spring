package com.kevinproject.backtienda.repository;

import com.kevinproject.backtienda.model.producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface productoRep extends JpaRepository<producto,Integer> {

}
