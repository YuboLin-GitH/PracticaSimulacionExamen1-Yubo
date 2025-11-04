package com.yubo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Cita")
public class Cita implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idCita")
    private int idCita;

    @Column(name = "fechaCita")
    private LocalDate fechaCita;

    @Column(name = "nombreEsp")
    private String nombreEsp;

    @ManyToOne
    @JoinColumn(name="idPaciente",referencedColumnName="idPaciente")
    private Paciente paciente;


    public Cita() {
    }

    public Cita(int idCita, LocalDate fechaCita, String nombreEsp, Paciente paciente) {
        this.idCita = idCita;
        this.fechaCita = fechaCita;
        this.nombreEsp = nombreEsp;
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "idCita=" + idCita +
                ", fechaCita=" + fechaCita +
                ", nombreEsp='" + nombreEsp + '\'' +
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


    public String getNombreEsp() {
        return nombreEsp;
    }

    public void setNombreEsp(String nombreEsp) {
        this.nombreEsp = nombreEsp;
    }


    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
}
