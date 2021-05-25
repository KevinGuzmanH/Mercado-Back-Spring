package com.kevinproject.backtienda.security.SecurityEntity;

import com.kevinproject.backtienda.model.producto;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String nombre;
    @NotNull
    @Column(unique = true)
    private String nombreUsuario;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Usuario_Rol", joinColumns = @JoinColumn(name = "Usuario_ID"),
            inverseJoinColumns = @JoinColumn(name = "Rol_ID"))
    private Set<Rol> roles = new HashSet<>();
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Usuario_Productos", joinColumns = @JoinColumn(name = "Usuario_ID"),
            inverseJoinColumns = @JoinColumn(name = "Productos_ID"))
    private Set<producto> productos = new HashSet<>();

    public Usuario(String nombre, String nombreUsuario, String email, String password) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public Set<producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<producto> productos) {
        this.productos = productos;
    }
}
