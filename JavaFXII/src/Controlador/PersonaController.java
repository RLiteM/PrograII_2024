/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.TablaPersona;

import java.net.URL;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import reporteprogramacion.factory;

/**
 * FXML Controller class
 *
 * @author vicen
 */
public class PersonaController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtEdad;
    @FXML
    private TableView<TablaPersona> tblPersona;
    @FXML
    private TableColumn<?, ?> idPersona;
    @FXML
    private TableColumn<?, ?> nombrePersona;
    @FXML
    private TableColumn<?, ?> cedulaPesona;
    @FXML
    private TableColumn<?, ?> edadPersona;
    private ObservableList<TablaPersona> listaPersona;

    private Integer idPErsonaV;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnAnular;
    @FXML
    private Button btnCancelar;
    @FXML
    private TableColumn<?, ?> usuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(() -> txtNombre.requestFocus());
        mostrar();
        btnGuardar.setVisible(true);
        btnAnular.setVisible(false);
        btnModificar.setVisible(false);
        btnCancelar.setVisible(false);
        navegarCampos(txtNombre, txtCedula);
        navegarCampos(txtCedula, txtEdad);
    }

    @FXML
    public void Insertar() {
        try {
            String nombre;
            Integer cedula, edad;
            nombre = txtNombre.getText();
            cedula = Integer.parseInt(txtCedula.getText());
            edad = Integer.parseInt(txtEdad.getText());
            if (CRUDs.CRUDSPersona.crear(nombre, cedula, edad)) {
                mostrar();
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Informacion xd");
                alerta.setHeaderText(null);
                alerta.setContentText("Operacion Exitosa");
                alerta.showAndWait();
                limpiar();
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Informacion IMPORTANTE");
                alerta.setHeaderText(null);
                alerta.setContentText("No eres un papulince :'v");
                alerta.showAndWait();
            }
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Informacion Importante");
            alerta.setHeaderText(null);
            alerta.setContentText("Error en: " + e);
            alerta.showAndWait();

        }
    }

    @FXML
    public void modificar() {
        try {
            String nombre;
            Integer cedula, edad;
            nombre = txtNombre.getText();
            cedula = Integer.parseInt(txtCedula.getText());
            edad = Integer.parseInt(txtEdad.getText());
            if (CRUDs.CRUDSPersona.actualizar(idPErsonaV, nombre, cedula, edad)) {
                mostrar();
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Informacion xd");
                alerta.setHeaderText(null);
                alerta.setContentText("La grasa lo respalda ;v");
                alerta.showAndWait();
                // aqui se agrega el resto

                btnGuardar.setVisible(true);
                btnAnular.setVisible(false);
                btnModificar.setVisible(false);
                btnCancelar.setVisible(false);

                limpiar();
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Informacion IMPORTANTE MAMAHUEVO");
                alerta.setHeaderText(null);
                alerta.setContentText("No eres un papulince :'v");
                alerta.showAndWait();
            }
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Informacion IMPORTANTE MAMAHUEVO");
            alerta.setHeaderText(null);
            alerta.setContentText("Error en: " + e);
            alerta.showAndWait();

        }
    }

    @FXML
    public void anular() {
        try {

            if (CRUDs.CRUDSPersona.anular(idPErsonaV)) {
                mostrar();
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Informacion xd");
                alerta.setHeaderText(null);
                alerta.setContentText("Registro Anulado");
                alerta.showAndWait();

                limpiar();
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Informacion IMPORTANTE MAMAHUEVO");
                alerta.setHeaderText(null);
                alerta.setContentText("Registro no anulado");
                alerta.showAndWait();
            }
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Informacion IMPORTANTE MAMAHUEVO");
            alerta.setHeaderText(null);
            alerta.setContentText("Error en: " + e);
            alerta.showAndWait();

        }
    }

    private void seleccionaModifcar(javafx.scene.input.MouseEvent event) {

    }

    public void limpiar() {
        txtNombre.setText("");
        txtCedula.setText("");
        txtEdad.setText("");
    }

    public void mostrar() {
        listaPersona = FXCollections.observableArrayList();
        for (Iterator it = CRUDs.CRUDSPersona.universo().iterator(); it.hasNext();) {
            Object[] item = (Object[]) it.next();
            listaPersona.add(new TablaPersona((Integer) item[0], (String) item[1], (Integer) item[2], (Integer) item[3], (String) item[4]));

        }
        this.idPersona.setCellValueFactory(new PropertyValueFactory("idPersona"));
        this.nombrePersona.setCellValueFactory(new PropertyValueFactory("nombrePersona"));
        this.cedulaPesona.setCellValueFactory(new PropertyValueFactory("cedulaPersona"));
        this.edadPersona.setCellValueFactory(new PropertyValueFactory("edadPersona"));
        this.usuario.setCellValueFactory(new PropertyValueFactory("usuario"));
        tblPersona.setItems(listaPersona);
    }

    @FXML
    public void cancelar() {
        btnGuardar.setVisible(true);
        btnAnular.setVisible(false);
        btnModificar.setVisible(false);
        btnCancelar.setVisible(false);
        txtNombre.setText("");
        txtEdad.setText("");
        txtCedula.setText("");

    }
    
    private void navegarCampos(TextField campoAnterior, TextField campoSiguiente){
        campoAnterior.setOnKeyPressed((event) -> {
            switch(event.getCode()){
                     case ENTER:
            campoSiguiente.requestFocus();
            break;
            default:
            break;
            }
       
        });
        
    }

    @FXML
    private void seleccionaModificar(MouseEvent event) {
        TablaPersona p = this.tblPersona.getSelectionModel().getSelectedItem();
        txtNombre.setText(p.getNombrePersona());
        txtCedula.setText(p.getCedulaPersona() + "");
        txtEdad.setText(p.getEdadPersona() + "");
        idPErsonaV = p.getIdPersona();
        btnGuardar.setVisible(false);
        btnAnular.setVisible(true);
        btnModificar.setVisible(true);
        btnCancelar.setVisible(true);
    }
    
    @FXML
        public void reporteCliente() throws ParseException {
        try {
            List lista = null;
            reporteprogramacion.ReporteProgramacion.ReporteCliente();
            lista = (factory.reporteCliente());
            if (!lista.isEmpty()) {
//                String rutaInforme = "Reportes\\reporteCliente.jrxml";
                JasperReport jasperReport = JasperCompileManager.compileReport("src/Reportes/ReporteCliente.jrxml");
//                JasperReport jasperReport = JasperCompileManager.compileReport("reporteCliente.jrxml");
//            Map<String, Object> parameters = new HashMap<>();
//            parameters.put("fecha1", f1);
//            parameters.put("fecha2", f23);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(lista));
                JasperViewer viewer = new JasperViewer(jasperPrint, false);
                viewer.setVisible(true);
//            JasperPrint informe = JasperFillManager.fillReport(rutainforme,parametros,miconeccion.conexion);

                // Export to PDF or display in a viewer
                // You can save it to a file, display it in a viewer, or use other means to work with the generated PDF.
            } else {
                System.out.println("No existe información en estas fechas");
            }
        } catch (JRException e) {
            System.out.println("Error al cargar el reporte: " + e);
        } catch (groovyjarjarcommonscli.ParseException ex) {
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the idPErsonaV
     */
    public Integer getIdPErsonaV() {
        return idPErsonaV;
    }

    /**
     * @param idPErsonaV the idPErsonaV to set
     */
    public void setIdPErsonaV(Integer idPErsonaV) {
        this.idPErsonaV = idPErsonaV;
    }

}
