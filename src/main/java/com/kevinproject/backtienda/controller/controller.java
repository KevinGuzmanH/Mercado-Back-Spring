package com.kevinproject.backtienda.controller;

import com.kevinproject.backtienda.model.producto;
import com.kevinproject.backtienda.service.productoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/tienda")
public class controller {

    @Autowired
    productoServ productoServ;
    //asdadpppapapa
    @GetMapping(path = "/productlist")
    public List<producto>productoList(){
        return productoServ.listarTodo();
    }

    @GetMapping(path = "/categoria/{categoria}")
    public List<producto> save(@PathVariable(name = "categoria",required = true)String categoria){
       return productoServ.findByCategoria(categoria);
    }
    @GetMapping(path = "/ofertas}")
    public List<producto> save(){
        return productoServ.findByOfertaIsTrue();
    }
    @GetMapping(path = "/getbyid/{id}")
    public Optional<producto> getbyid(@PathVariable(required = true, name = "id")int id ){
        return productoServ.listarPorId(id);
    }
    @PutMapping(path = "/updateProduct")
    public producto actualizar(@RequestBody producto P){
        return productoServ.edit(P);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deletebyid(@PathVariable(name = "id",required = true)int id){
        productoServ.borrarPorId(id);
    }

}
