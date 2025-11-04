package com.yubo.Controller;


import com.yubo.DAO.*;
import com.yubo.domain.Cita;
import com.yubo.domain.Especialidades;
import com.yubo.domain.Paciente;
import com.yubo.util.AlertUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class CitaController {
    private final MySQL_PacienteInterface mySQL_PacienteInterface = new MySQL_PacienteDAO();
    private final MySQL_CitaInterface mySQL_CitaInterface = new MySQL_CitaDAO();


    @FXML
    public TextField tfTelefono, tfNombre, tfDireccion, tfDNI, tfNumeroCita;


    @FXML
    public Button btNuevaCita, btBorrarCita, btModificarCita, btVerCita, btLimbiar;


    @FXML
    public DatePicker dpFechaCita;

    @FXML
    public ComboBox<Especialidades> cbEspecialidad;

    @FXML
    public TableView<Cita> tvCitasPaciente;
    @FXML
    private TableColumn<Cita, Integer> colIdCita;
    @FXML
    private TableColumn<Cita, Date> colFecha;
    @FXML
    private TableColumn<Cita, String> colEspecialidad;


    private Paciente paciente;

    private Cita citaSeleccionada;

    public CitaController() {
    }


    @FXML
    public void initialize() {
        tfNombre.setDisable(true);
        tfDireccion.setDisable(true);
        tfTelefono.setDisable(true);
        tfNumeroCita.setDisable(true);

        colIdCita.setCellValueFactory(new PropertyValueFactory<>("idCita"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaCita"));
        colEspecialidad.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                        cellData.getValue().getEspecialidad().getNombreEspecialidad())
        );


        cargarEspecialidades();

        enlazarSeleccionDeTabla();
        limpiarCajas();
        tfDNI.setOnKeyPressed(this::manejarEnterParaVerCita);
    }

    private void manejarEnterParaVerCita(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            verPaciente();
        }
    }



    private void mostrarDatosPaciente() {
        tfNombre.setText(paciente.getNombre());
        tfDireccion.setText(paciente.getDireccion());
        tfTelefono.setText(String.valueOf(paciente.getTelefono()));
        tfDNI.setText(paciente.getDni());

    }

    private void cargarEspecialidades() {
        EspecialidadDAO especialidadDAO = new EspecialidadDAO();
        try {

            List<Especialidades> especialidades = especialidadDAO.obtenerEspecialidad();
            if (especialidades.isEmpty()) {
                AlertUtils.mostrarError("Error al obtener las especialidades");
                return;
            }

            cbEspecialidad.getItems().addAll(especialidades);
            cbEspecialidad.setValue(null);

            for (Especialidades esp : especialidades) {
                    cbEspecialidad.setValue(esp);
                    break;
            }


        } catch (Exception e) {

            AlertUtils.mostrarError("Error：" + e.getMessage());
            e.printStackTrace();
        }
    }



    private void enlazarSeleccionDeTabla() {
        tvCitasPaciente.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                citaSeleccionada = newVal;

                tfNumeroCita.setText(String.valueOf(newVal.getIdCita()));

                if (newVal.getFechaCita() != null) {
                    dpFechaCita.setValue(newVal.getFechaCita());
                }

                for (Especialidades esp : cbEspecialidad.getItems()) {
                    if (esp.getNombreEspecialidad().equals(newVal.getEspecialidad().getNombreEspecialidad())) {
                        cbEspecialidad.setValue(esp);
                        break;
                    }
                }
            }
        });
    }


    @FXML
    public  void verPaciente(){
        try {
            String dniIngresado = tfDNI.getText().trim();
            if (dniIngresado.isEmpty()) {
                AlertUtils.mostrarError("Introduce un DNI válido");
                return;
            }


            Paciente nuevoPaciente = mySQL_PacienteInterface.buscarPorDni(dniIngresado);


            if (nuevoPaciente == null) {
                AlertUtils.mostrarError("No se encontró paciente con ese DNI");
                return;
            }


            this.paciente = nuevoPaciente;
            mostrarDatosPaciente();



        } catch (Exception e) {
            AlertUtils.mostrarError("Error al buscar paciente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void verCita() {


        try {
            String dniIngresado = tfDNI.getText().trim();
            if (dniIngresado.isEmpty()) {
                AlertUtils.mostrarError("Introduce un DNI válido");
                return;
            }


            Paciente nuevoPaciente = mySQL_PacienteInterface.buscarPorDni(dniIngresado);


            if (nuevoPaciente == null) {
                AlertUtils.mostrarError("No se encontró paciente con ese DNI");
                return;
            }


            List<Cita> citas = mySQL_CitaInterface.obtenerCitaPorPacienteId(paciente.getIdPaciente());


            LocalDate hoy = LocalDate.now();
            boolean hayCitaHoy = citas.stream().anyMatch(cita -> {
                LocalDate fechaCita = cita.getFechaCita();
                return fechaCita != null && fechaCita.equals(hoy);
            });

            if (hayCitaHoy) {
                AlertUtils.mostrarInformacion("¡Tienes una cita para hoy!");
            }


            tvCitasPaciente.setItems(FXCollections.observableArrayList(citas));


        } catch (Exception e) {
            AlertUtils.mostrarError("Error al buscar paciente: " + e.getMessage());
            e.printStackTrace();
        }
    }







    @FXML
    public void limpiarCajas() {
        tfNumeroCita.clear();
        dpFechaCita.setValue(null);
        cbEspecialidad.setValue(null);
    }

}
 
