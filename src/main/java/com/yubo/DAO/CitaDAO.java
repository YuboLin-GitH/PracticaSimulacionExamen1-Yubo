package com.yubo.DAO;

import com.yubo.domain.Cita;
import com.yubo.domain.Especialidades;
import com.yubo.domain.Paciente;
import com.yubo.util.R;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CitaDAO {
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
        if (conexion != null && !conexion.isClosed()){
            conexion.close();
        }
    }


    public List<Cita> obtenerCitaPorPacienteId(int pacienteId) throws SQLException {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT c.idCita, c.fechaCita, e.idEspecialidad, e.nombreEspecialidad, c.idPaciente " +
                "FROM Citas c JOIN Especialidades e ON c.idEspecialidad = e.idEspecialidad WHERE c.idPaciente = ?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, pacienteId);
        ResultSet rs = sentencia.executeQuery();

        while (rs.next()) {
            Cita cita = new Cita();
            cita.setIdCita(rs.getInt("idCita"));
            cita.setFechaCita(rs.getDate("fechaCita").toLocalDate());
            Especialidades especialidad = new Especialidades();
            especialidad.setIdEspecialidad(rs.getInt("idEspecialidad"));
            especialidad.setNombreEspecialidad(rs.getString("nombreEspecialidad"));
            cita.setEspecialidad(especialidad);

            Paciente paciente = new Paciente();
            paciente.setIdPaciente(rs.getInt("idPaciente"));
            cita.setPaciente(paciente);

            citas.add(cita);
        }
        return citas;
    }


}
