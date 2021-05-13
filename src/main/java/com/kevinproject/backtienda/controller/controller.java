package com.kevinproject.backtienda.controller;

import com.kevinproject.backtienda.model.producto;
import com.kevinproject.backtienda.service.productoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "https://dientabacksp.netlify.app/")
@RestController
@RequestMapping(path = "/tienda")
public class controller {

    @Autowired
    productoServ productoServ;

    @GetMapping(path = "/tabla")
    public List<producto>productoList(){
        return productoServ.listarTodo();
    }

    @PostMapping(path = "/guardar")
    public producto save(@RequestBody producto P){
       return productoServ.guardar(P);
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
    @GetMapping(path = "/balancetotal")
    public int balancetotal(){
        return productoServ.balanceTienda();
    }
    @PutMapping(path = "/supply/{unidades}/value{valorunidad}")
    public void supplyProduct(@PathVariable(name = "unidades")int unds,@PathVariable(name = "valorunidad")double valorU,@RequestBody producto producto){
        int id = producto.getId();
        productoServ.abastecerProducto(id,unds,valorU);
    }
    @GetMapping(path = "/stock")
    public int getStock(){
        return productoServ.stock();
    }
}
