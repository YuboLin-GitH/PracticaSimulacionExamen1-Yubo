package com.yubo.DAO;

import com.yubo.domain.Citas;
import org.hibernate.Session;

import java.util.List;

public interface Hibernate_CitaInterface {
    void insertarCita(Session session, Citas c);


    void modificarCita(Session session, Citas c);

    void borrarCita(Session session, Citas c);

    List<Citas> listarCita(Session session);
    
}
