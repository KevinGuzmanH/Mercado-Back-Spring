package com.kevinproject.backtienda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
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
    private double Balance;


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

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }
}
