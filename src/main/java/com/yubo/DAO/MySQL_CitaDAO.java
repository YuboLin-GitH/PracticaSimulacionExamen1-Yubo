package com.yubo.DAO;

import com.yubo.Connection.MySQL_ConnectionDB;
import com.yubo.domain.Cita;
import com.yubo.domain.Especialidades;
import com.yubo.domain.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQL_CitaDAO implements MySQL_CitaInterface {


    @Override
    public List<Cita> obtenerCitaPorPacienteId(int pacienteId) throws SQLException {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT c.idCita, c.fechaCita, e.idEspecialidad, e.nombreEspecialidad, c.idPaciente " +
                "FROM Citas c JOIN Especialidades e ON c.idEspecialidad = e.idEspecialidad WHERE c.idPaciente = ?";

        try (Connection conn = MySQL_ConnectionDB.conectar();
             PreparedStatement sentencia = conn.prepareStatement(sql)) {

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
        }
        // 这里不用手动close，try-with-resources会自动关闭
        return citas;
    }


}
