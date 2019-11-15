package com.example.demowebapi.models;

import com.google.gson.annotations.SerializedName;

public class Alumno {

    private int id;
    private String nombre;
    private String email;
    private String domicilio;
    private int activo;
    private int favorito;
    private String foto;

    public Alumno(){ }

    public Alumno(String nombre, String email, String domicilio, int activo, int favorito, String foto) {
        this.nombre = nombre;
        this.email = email;
        this.domicilio = domicilio;
        this.activo = activo;
        this.favorito = favorito;
        this.foto = foto;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getFavorito() {
        return favorito;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
