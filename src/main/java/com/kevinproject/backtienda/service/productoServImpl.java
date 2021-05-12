package com.kevinproject.backtienda.service;

import com.kevinproject.backtienda.model.producto;
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

    public int balanceTienda(){
        int balanceTotal = 0;
        List<producto> productos= productoRep.findAll();
        for (int i=0; i<productos.size() ;i++){
            balanceTotal+= productos.get(i).getBalance();
        }
        return balanceTotal;
    }
}
