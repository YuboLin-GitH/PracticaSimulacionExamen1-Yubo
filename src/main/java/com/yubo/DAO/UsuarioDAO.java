package com.yubo.DAO;

import com.yubo.domain.Paciente;
import com.yubo.util.R;
import org.apache.commons.codec.digest.DigestUtils;


import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class UsuarioDAO {

    private Connection conexion;

    public void conectar() throws ClassNotFoundException, SQLException, IOException {
        Properties configuration = new Properties();
        configuration.load(R.getProperties("database.properties"));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
    }

    public void desconectar() throws SQLException {
        if (conexion != null) conexion.close();
    }

    private Paciente resultSetToPaciente(ResultSet resultado) throws SQLException {
        Paciente p = new Paciente();
        p.setIdPaciente(resultado.getInt("idPaciente"));
        p.setDni(resultado.getString("dni"));
        p.setNombre(resultado.getString("nombre"));
        p.setPassword(resultado.getString("pass"));
        p.setDireccion(resultado.getString("direccion"));
        p.setTelefono(resultado.getString("telefono"));
        return p;
    }

    public Paciente buscarPorDni(String dni) throws SQLException {
        String sql = "SELECT * FROM pacientes WHERE dni = ?";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, dni);
            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                return resultSetToPaciente(resultado);
            }
        }
        return null;
    }





}
