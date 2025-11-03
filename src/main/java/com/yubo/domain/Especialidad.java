package com.yubo.domain;


public class Especialidad {
    private int idEspecialidad;
    private String nombreEsp;


    public Especialidad() {
    }

    public Especialidad(int idEsp, String nombreEsp) {
        this.idEspecialidad = idEsp;
        this.nombreEsp = nombreEsp;
    }

    public int getIdEsp() {
        return idEspecialidad;
    }

    public void setIdEsp(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombreEsp() {
        return nombreEsp;
    }

    public void setNombreEsp(String nombreEsp) {
        this.nombreEsp = nombreEsp;
    }


    @Override
    public String toString() {
        return "Especialidad{" +
                "idEspecialidad=" + idEspecialidad +
                ", nombreEsp='" + nombreEsp + '\'' +
                '}';
    }
}
