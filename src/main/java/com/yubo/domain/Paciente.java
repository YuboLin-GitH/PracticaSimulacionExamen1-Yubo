package com.yubo.domain;


import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idPaciente")
    private int idPaciente;

    @Column(name = "dni")
    private String dni;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "password")
    private String password;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private int telefono;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Cita> citas ;

    public Paciente() {
    }

    public Paciente(int idPaciente, String dni, String nombre, String password, String direccion, int telefono) {
        this.idPaciente = idPaciente;
        this.dni = dni;
        this.nombre = nombre;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }


    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente=" + idPaciente +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono=" + telefono +
                '}';
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}
