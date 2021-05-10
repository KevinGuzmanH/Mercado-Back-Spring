

package com.kevinproject.backtienda.service;

import com.kevinproject.backtienda.model.producto;

import java.util.List;
import java.util.Optional;

public interface productoServ  {
    List<producto> listarTodo();
    Optional<producto> listarPorId(Integer id);
    void borrarPorId(Integer id);
    void guardar(producto p);
}
