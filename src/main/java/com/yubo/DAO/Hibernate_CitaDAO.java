package com.yubo.DAO;


import com.yubo.domain.Citas;
import org.hibernate.Session;

import java.util.List;

public class Hibernate_CitaDAO implements Hibernate_CitaInterface {
    @Override
    public void insertarCita(Session session, Citas c)
    {
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
    }


    @Override
    public void modificarCita(Session session, Citas c)
    {
        session.beginTransaction();
        session.update(c);
        session.getTransaction().commit();
    }

    @Override
    public void borrarCita(Session session, Citas c)
    {

        session.beginTransaction();
        session.delete(c);
        session.getTransaction().commit();
    }

    @Override
    public List<Citas> listarCita(Session session)
    {
        List<Citas> lista = session.createQuery("from Citas ", Citas.class).list();
        return lista;

        //list.forEach(System.out::println);//version 1.8 de java
    }




}
