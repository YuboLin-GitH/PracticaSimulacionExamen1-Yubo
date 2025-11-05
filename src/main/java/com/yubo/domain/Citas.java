package com.yubo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Citas")
public class Citas implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idCita")
    private int idCita;

    @Column(name = "fechaCita")
    private LocalDate fechaCita;

    @ManyToOne
    @JoinColumn(name = "idEspecialidad", referencedColumnName = "idEspecialidad")
    private Especialidades especialidad;

    @ManyToOne
    @JoinColumn(name = "idPaciente", referencedColumnName = "idPaciente")
    private Paciente paciente;


    public Citas() {
    }

    public Citas(int idCita, LocalDate fechaCita, Especialidades especialidades, Paciente paciente) {
        this.idCita = idCita;
        this.fechaCita = fechaCita;
        this.especialidad = especialidades;
        this.paciente = paciente;
    }
    @Override
    public String toString() {
        return "Cita{" +
                "idCita=" + idCita +
                ", fechaCita=" + fechaCita +
                ", especialidad=" + (especialidad != null ? especialidad.getNombreEspecialidad() : "null") +
                ", paciente=" + paciente +
                '}';
    }
    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }


    public Especialidades getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidades especialidad) {
        this.especialidad = especialidad;
    }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
}
