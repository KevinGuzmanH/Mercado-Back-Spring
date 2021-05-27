package com.kevinproject.backtienda.service;

import com.kevinproject.backtienda.model.producto;
import com.kevinproject.backtienda.repository.productoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class productoServImpl implements productoServ {

    @Autowired
    com.kevinproject.backtienda.repository.productoRep productoRep;

    @Override
    public List<producto> listarTodo() {
      return productoRep.findAll();
    }

    @Override
    public Optional<producto> listarPorId(Integer id) {
        return productoRep.findById(id);
    }

    @Override
    public void borrarPorId(Integer id) {
        productoRep.deleteById(id);
    }

    @Override
    public producto guardar(producto p) {
        return productoRep.save(p);
    }

    @Override
    public producto edit(producto p) {
        return productoRep.save(p);
    }

    @Override
    public List<producto> getOfertas() {
        return productoRep.findByOfertaIsTrue();
    }

    @Override
    public List<producto> getbyCategoria(String categoria) {
        return productoRep.findByCategoria(categoria);
    }

}
