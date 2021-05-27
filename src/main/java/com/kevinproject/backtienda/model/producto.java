package com.kevinproject.backtienda.model;

import javax.persistence.*;

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
    private String categoria;
    @Column(nullable = false)
    private String Imagen;
    @Column(nullable = false)
    private Boolean oferta;
    @Column(nullable = false)
    private String Descripcion;

    public Boolean getOferta() {
        return oferta;
    }

    public void setOferta(Boolean oferta) {
        oferta = oferta;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getCategoria() {return categoria;}

    public void setCategoria(String categoria) {
        categoria = categoria;
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
