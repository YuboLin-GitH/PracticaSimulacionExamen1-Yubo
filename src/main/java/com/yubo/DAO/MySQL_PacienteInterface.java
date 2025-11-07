package com.yubo.DAO;

import com.yubo.domain.Paciente;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ClassName: MySQL_PacienteInterface
 * Package: com.yubo.DAO
 * Description:
 *
 * @Author linlin
 * @Create 04/11/2025 21:50
 * @Version 1.0
 */
public interface MySQL_PacienteInterface {

    Paciente resultadoPaciente(ResultSet resultado) throws SQLException;

    Paciente buscarPorDni(String dni) throws SQLException;

    void crearPaciente(Paciente paciente) throws SQLException;
}
