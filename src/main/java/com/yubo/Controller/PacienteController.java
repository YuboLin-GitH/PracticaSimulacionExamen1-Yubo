package com.yubo.Controller;

import com.yubo.DAO.MySQL_PacienteDAO;
import com.yubo.DAO.MySQL_PacienteInterface;
import com.yubo.domain.Paciente;
import com.yubo.util.AlertUtils;
import com.yubo.util.HashUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.nio.Buffer;
import java.sql.SQLException;

/**
 *
 * ClassName: PacienteController
 * Package: com.yubo.Controller
 * Description:
 *
 * @Author linlin
 * @Create 06/11/2025 21:30
 * @Version 1.0
 */
public class PacienteController {
    private final MySQL_PacienteInterface mySQL_PacienteInterface = new MySQL_PacienteDAO();

    @FXML
    public TextField tfTelefono, tfNombre, tfDireccion, tfDNI , tfPass;


    @FXML
    public Button btCrearPaciente;


    public PacienteController() {
    }


    @FXML
    private void nuevoPaciente() throws SQLException {

        String dniIngresado = tfDNI.getText().trim();
        String passIngresado = tfPass.getText();
        String encryptedPw = HashUtil.sha256(passIngresado);
        String nombreIngresado = tfNombre.getText();
        String direccionIngresado = tfDireccion.getText();
        String telefonoIngresado = tfTelefono.getText();

        if (dniIngresado.isEmpty()) {
            AlertUtils.mostrarError("El DNI no puede estar vacío.");
            return;
        }
        if (passIngresado.isEmpty()) {
            AlertUtils.mostrarError("La contraseña no puede estar vacía.");
            return;
        }
        if (nombreIngresado.isEmpty()) {
            AlertUtils.mostrarError("El nombre no puede estar vacío.");
            return;
        }


        Paciente p = new Paciente();
        p.setDni(dniIngresado);
        p.setPass(encryptedPw);
        p.setNombre(nombreIngresado);
        p.setDireccion(direccionIngresado);
        p.setTelefono(telefonoIngresado);


        try {
            mySQL_PacienteInterface.crearPaciente(p);
            AlertUtils.mostrarInformacion("Paciente creado con éxito!");

        } catch (SQLException e) {
            AlertUtils.mostrarError("No se pudo crear el paciente: " + e.getMessage());
            e.printStackTrace();
        }


    }


    @FXML
    private void limpia() throws SQLException {
        tfDNI.clear();
        tfPass.clear();
        tfNombre.clear();
        tfDireccion.clear();
        tfTelefono.clear();

    }

}
