package com.kevinproject.backtienda.controller;

import com.kevinproject.backtienda.model.producto;
import com.kevinproject.backtienda.service.productoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
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
}
