package com.yubo.DAO;

import com.yubo.domain.Cita;
import org.hibernate.Session;

import java.util.List;

public interface Hibernate_CitaInterface {
    void insertarCita(Session session, Cita c);


    List<Cita> listarCita(Session session);
}
