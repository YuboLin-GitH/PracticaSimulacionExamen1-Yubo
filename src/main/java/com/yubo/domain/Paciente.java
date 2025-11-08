package com.yubo.domain;


import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idPaciente")
    private int idPaciente;

    @Column(name = "dni")
    private String dni;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "pass")
    private String pass;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;

    /*
    @Column(name = "sancionado")
    private boolean sancionado;
    */

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Citas> citas ;

    public Paciente() {
    }

    public Paciente(int idPaciente, String dni, String nombre, String pass, String direccion, String telefono) {
        this.idPaciente = idPaciente;
        this.dni = dni;
        this.nombre = nombre;
        this.pass = pass;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente=" + idPaciente +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", password='" + pass + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono=" + telefono +
                '}';
    }

    public List<Citas> getCitas() {
        return citas;
    }

    public void setCitas(List<Citas> citas) {
        this.citas = citas;
    }
}
