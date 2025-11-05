package com.yubo.DAO;


import com.yubo.domain.Cita;
import org.hibernate.Session;

import java.util.List;

public class Hibernate_CitaDAO implements Hibernate_CitaInterface {
    @Override
    public void insertarCita(Session session, Cita c)
    {
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
    }



    @Override
    public List<Cita> listarCita(Session session)
    {
        List<Cita> lista = session.createQuery("from Cita ", Cita.class).list();
        return lista;

        //list.forEach(System.out::println);//version 1.8 de java
    }




}
