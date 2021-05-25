package com.kevinproject.backtienda.controller;

import com.kevinproject.backtienda.model.producto;
import com.kevinproject.backtienda.service.productoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/tienda")
public class controller {

    @Autowired
    productoServ productoServ;

    @GetMapping(path = "/productlist")
    public List<producto>productoList(){
        return productoServ.listarTodo();
    }

    @GetMapping(path = "/getbyid/{id}")
    public Optional<producto> getbyid(@PathVariable(required = true, name = "id")int id ){
        return productoServ.listarPorId(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/updateProduct")
    public producto actualizar(@RequestBody producto P){
        return productoServ.edit(P);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/delete/{id}")
    public void deletebyid(@PathVariable(name = "id",required = true)int id){
        productoServ.borrarPorId(id);
    }

    @GetMapping(path = "/offers")
    public List<producto> gerOffers(){
        return productoServ.getOfertas();
    }
}
