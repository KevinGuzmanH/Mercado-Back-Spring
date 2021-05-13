

package com.kevinproject.backtienda.service;

import com.kevinproject.backtienda.model.producto;

import java.util.List;
import java.util.Optional;

public interface productoServ  {
    List<producto> listarTodo();
    Optional<producto> listarPorId(Integer id);
    void borrarPorId(Integer id);
    producto guardar(producto p);
    producto edit(producto p);
    int balanceTienda();
    int stock();
    void abastecerProducto(int id, Integer nPDTS,double costoU);

}
