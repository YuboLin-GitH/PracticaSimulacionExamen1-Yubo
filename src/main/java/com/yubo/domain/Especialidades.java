package com.yubo.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Especialidades")
public class Especialidades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEspecialidad")
    private int idEspecialidad;

    @Column(name = "nombreEspecialidad")
    private String nombreEspecialidad;

    @OneToMany(mappedBy = "especialidad", cascade = CascadeType.ALL)
    private List<Cita> citas;


    public Especialidades() {
    }

    public Especialidades(int idEspecialidad, String nombreEspecialidad) {
        this.idEspecialidad = idEspecialidad;
        this.nombreEspecialidad = nombreEspecialidad;
    }


    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }


    @Override
    public String toString() {
        return nombreEspecialidad;
    }
}
