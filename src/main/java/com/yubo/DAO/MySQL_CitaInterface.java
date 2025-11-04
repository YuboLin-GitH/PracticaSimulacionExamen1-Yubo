package com.yubo.DAO;

import com.yubo.domain.Cita;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * ClassName: MySQL_CitaInterface
 * Package: com.yubo.DAO
 * Description:
 *
 * @Author linlin
 * @Create 04/11/2025 22:08
 * @Version 1.0
 */
public interface MySQL_CitaInterface {

    List<Cita> obtenerCitaPorPacienteId(int pacienteId) throws SQLException;
}
