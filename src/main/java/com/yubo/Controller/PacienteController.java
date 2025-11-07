package com.yubo.Controller;

import com.yubo.DAO.MySQL_PacienteDAO;
import com.yubo.DAO.MySQL_PacienteInterface;
import com.yubo.domain.Paciente;
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
        String nombreIngresado = tfNombre.getText();
        String direccionIngresado = tfDireccion.getText();
        String telefonoIngresado = tfTelefono.getText();

        Paciente p = new Paciente();
        p.setDni(dniIngresado);
        p.setPass(passIngresado);
        p.setNombre(nombreIngresado);
        p.setDireccion(direccionIngresado);
        p.setTelefono(telefonoIngresado);

        mySQL_PacienteInterface.crearPaciente(p);

    }

}
