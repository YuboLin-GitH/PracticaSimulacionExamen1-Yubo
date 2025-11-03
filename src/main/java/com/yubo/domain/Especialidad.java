package com.yubo.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Especialidad {
    @JsonProperty("idEspecialidad")
    private int idEspecialidad;

    private String nombreEspecilidad;


    public Especialidad() {
    }

    public Especialidad(int idEspecialidad, String nombreEspecilidad) {
        this.idEspecialidad = idEspecialidad;
        this.nombreEspecilidad = nombreEspecilidad;
    }


    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombreEspecilidad() {
        return nombreEspecilidad;
    }

    public void setNombreEspecilidad(String nombreEspecilidad) {
        this.nombreEspecilidad = nombreEspecilidad;
    }


    @Override
    public String toString() {
        return nombreEspecilidad;
    }
}
