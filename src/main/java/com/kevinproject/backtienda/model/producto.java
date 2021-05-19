package com.kevinproject.backtienda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class producto {
    @Id
    private int Id;
    @Column(nullable = false)
    private String Nombre;
    @Column(nullable = false)
    private Double Valor;
    @Column(nullable = false)
    private int Existencias;
    @Column(nullable = false)
    private String Categoria;
    @Column(nullable = false)
    private String Imagen;
    @Column(nullable = false)
    private Boolean Oferta;
    @Column(nullable = false)
    private String Descripcion;

    public Boolean getOferta() {
        return Oferta;
    }

    public void setOferta(Boolean oferta) {
        Oferta = oferta;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getCategoria() {return Categoria;}

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double valor) {
        Valor = valor;
    }

    public int getExistencias() {
        return Existencias;
    }

    public void setExistencias(int existencias) {
        Existencias = existencias;
    }

}
