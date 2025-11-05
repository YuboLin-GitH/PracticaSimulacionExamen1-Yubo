package com.yubo.DAO;

import com.yubo.Connection.MySQL_ConnectionDB;
import com.yubo.domain.Paciente;


import java.sql.*;


public class MySQL_PacienteDAO implements MySQL_PacienteInterface {



    @Override
    public Paciente resultadoPaciente(ResultSet resultado) throws SQLException {
        Paciente p = new Paciente();
        p.setIdPaciente(resultado.getInt("idPaciente"));
        p.setDni(resultado.getString("dni"));
        p.setNombre(resultado.getString("nombre"));
        p.setPass(resultado.getString("pass"));
        p.setDireccion(resultado.getString("direccion"));
        p.setTelefono(resultado.getString("telefono"));
        return p;
    }


    @Override
    public Paciente buscarPorDni(String dni) throws SQLException {
        String sql = "SELECT * FROM pacientes WHERE dni = ?";
        try (Connection conn = MySQL_ConnectionDB.conectar();
             PreparedStatement sentencia = conn.prepareStatement(sql)) {
            sentencia.setString(1, dni);
            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                return resultadoPaciente(resultado);
            }
        }
        return null;
    }




}
