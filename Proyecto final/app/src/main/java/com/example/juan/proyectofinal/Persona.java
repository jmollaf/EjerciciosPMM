package com.example.juan.proyectofinal;

public class Persona {
    private String nombre;
    private String fecha;
    private String pais;
    private String sexo;
    private String ingles;


    public Persona(String nombre, String fecha, String pais, String sexo, String ingles) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.pais = pais;
        this.sexo = sexo;
        this.ingles = ingles;
    }

    public Persona() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getIngles() {
        return ingles;
    }

    public void setIngles(String ingles) {
        this.ingles = ingles;
    }
}
