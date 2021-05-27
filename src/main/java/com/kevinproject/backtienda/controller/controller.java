package com.kevinproject.backtienda.controller;

import com.kevinproject.backtienda.model.producto;
import com.kevinproject.backtienda.service.productoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(path = "/categoria/{categoria}")
    public List<producto>productoList(@PathVariable(name = "categoria")String categoria){
        return productoServ.getbyCategoria(categoria);
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

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deletebyid(@PathVariable(name = "id",required = true)int id){
        productoServ.borrarPorId(id);
        return new ResponseEntity("Producto Borrado", HttpStatus.OK);
    }

    @GetMapping(path = "/offers")
    public List<producto> gerOffers(){
        return productoServ.getOfertas();
    }
}
