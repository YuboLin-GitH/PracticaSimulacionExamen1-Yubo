package com.yubo.Controller;


import com.yubo.DAO.*;
import com.yubo.domain.Citas;
import com.yubo.domain.Especialidades;
import com.yubo.domain.Paciente;
import com.yubo.util.AlertUtils;
import com.yubo.util.HibernateUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.hibernate.Session;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CitaController {
    private final MySQL_PacienteInterface mySQL_PacienteInterface = new MySQL_PacienteDAO();
    private final MySQL_CitaInterface mySQL_CitaInterface = new MySQL_CitaDAO();
    private final MongoDB_CitaInterface mongoDB_CitaInterface = new MongoDB_CitaDAO();
    private final Hibernate_CitaInterface hibernateCitaInterface = new Hibernate_CitaDAO();


    @FXML
    public TextField tfTelefono, tfNombre, tfDireccion, tfDNI, tfNumeroCita;


    @FXML
    public Button btNuevaCita, btBorrarCita, btModificarCita, btVerCita, btLimbiar;


    @FXML
    public DatePicker dpFechaCita;

    @FXML
    public ComboBox<Especialidades> cbEspecialidad;

    @FXML
    public TableView<Citas> tvCitasPaciente;
    @FXML
    private TableColumn<Citas, Integer> colIdCita;
    @FXML
    private TableColumn<Citas, Date> colFecha;
    @FXML
    private TableColumn<Citas, String> colEspecialidad;


    private Paciente paciente;

    private Citas citaSeleccionada;

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

    public void cargarDatos() {
        //modoEdicion(false);
        tvCitasPaciente.getItems().clear();

        try (Session session = HibernateUtil.getSession()){
            List<Citas> citas= hibernateCitaInterface.listarCita(session);
            List<Citas> estePaciente = new ArrayList<>();

            for (Citas c : citas) {
                if (c.getPaciente() != null && c.getPaciente().getIdPaciente() == paciente.getIdPaciente()) {
                    estePaciente.add(c);
                }
            }

            tvCitasPaciente.setItems(FXCollections.observableList(estePaciente));
        }
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


            List<Citas> citas = mySQL_CitaInterface.obtenerCitaPorPacienteId(paciente.getIdPaciente());


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
    private  void nuevaCita() {
        try {

            LocalDate fechaSeleccionada = dpFechaCita.getValue();
            Especialidades espSeleccionada = cbEspecialidad.getValue();

            if (fechaSeleccionada == null || espSeleccionada == null) {
                AlertUtils.mostrarError("Elegir fecha de cita o Especialidad");
                return;
            }
            Citas c = new Citas();

            c.setFechaCita(fechaSeleccionada);
            c.setEspecialidad(espSeleccionada);
            c.setPaciente(paciente);

            try(Session session = HibernateUtil.getSession()) {

                hibernateCitaInterface.insertarCita(session, c);

                AlertUtils.mostrarInformacion("Cita insertada correctamente");
                cargarDatos();
                limpiarCajas();

            }catch (Exception e){
                System.out.println("Error de Insertar Cita");
            }

            boolean mongoOK = mongoDB_CitaInterface.insertCita(c);
            if (mongoOK) {
                AlertUtils.mostrarInformacion("Cita registrada correctamente en MySQL y MongoDB.");
            } else {
                AlertUtils.mostrarInformacion("Cita registrada en MySQL, pero falló al guardarla en MongoDB.");
            }


        }catch (Exception e) {
            AlertUtils.mostrarError("Error al crear cita: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    private void modificarCita(){
        if (citaSeleccionada == null) {
            AlertUtils.mostrarError("El seleccionado no existe");
            return;
        }
        LocalDate fechaModificada = dpFechaCita.getValue();
        Especialidades espModificada = cbEspecialidad.getValue();
        if (fechaModificada == null || espModificada == null) {
            AlertUtils.mostrarError("Eliger bien cita y especificada");
            return;
        }
        try (Session session = HibernateUtil.getSession()) {


            citaSeleccionada.setIdCita(citaSeleccionada.getIdCita());
            citaSeleccionada.setFechaCita(fechaModificada);
            citaSeleccionada.setEspecialidad(espModificada);
            citaSeleccionada.setPaciente(paciente);


            hibernateCitaInterface.modificarCita(session,citaSeleccionada);
            AlertUtils.mostrarInformacion("Cita actualizada");


            cargarDatos();
            limpiarCajas();
            citaSeleccionada = null;
        } catch (Exception e) {
            AlertUtils.mostrarError("Error：" + e.getMessage());

        }
    }

    @FXML
    private void borrarCita(){
        if (citaSeleccionada == null) {
            AlertUtils.mostrarError("el seleccionado no existe");
            return;
        }
        try (Session session = HibernateUtil.getSession()){


            hibernateCitaInterface.borrarCita(session,citaSeleccionada);
            AlertUtils.mostrarInformacion("Cita eliminada");


            cargarDatos();
            limpiarCajas();
            citaSeleccionada = null;
        } catch (Exception e) {
            AlertUtils.mostrarError("Error：" + e.getMessage());
        }

    }


    @FXML
    public void limpiarCajas() {
        tfNumeroCita.clear();
        dpFechaCita.setValue(null);
        cbEspecialidad.setValue(null);
    }

}
 
