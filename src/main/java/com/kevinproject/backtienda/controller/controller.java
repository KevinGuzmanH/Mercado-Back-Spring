package com.kevinproject.backtienda.controller;

import com.kevinproject.backtienda.model.producto;
import com.kevinproject.backtienda.service.productoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/tabla")
public class controller {

    @Autowired
    productoServ productoServ;

    @GetMapping
    public List<producto>productoList(){
        return productoServ.listarTodo();
    }
}
