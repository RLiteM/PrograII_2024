/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import CRUDs.CRUDUsuario;
import CRUDs.Login;
import Modelo.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author IngeMayk
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasenia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void cdmIrPrincipal() throws IOException {
        if (txtContrasenia.getText().equals("") | txtUsuario.getText().equals("")) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("Erro Ingrese todos los datos");
            alerta.showAndWait();
        } else {
            POJOs.Usuario usuarioSlct = CRUDUsuario.select1(txtUsuario.getText());
            String psswrd = Login.sha1(txtContrasenia.getText());
            if (usuarioSlct == null) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText(null);
                alerta.setContentText("El usuario no existe");
                alerta.showAndWait();
            } else if (usuarioSlct.getContrasenia().equals(psswrd)) {
                String nombreUsuario = CRUDUsuario.select1(txtUsuario.getText()).getUsuario();
                Session.setNombreUsuario(nombreUsuario);
                txtUsuario.setText("");
                txtContrasenia.setText("");
                // cargar el Archivo
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Menu.fxml"));
                Parent root = loader.load();
// crear una nueva ventana
                Stage stage = new Stage();
                stage.setTitle("Menu");
                // crear una nueva escena con el contenido cargado

                Scene scene = new Scene(root);
                stage.setScene(scene);

                // mostrar la nueva ventana
                stage.show();
                Stage loginStage = (Stage) txtUsuario.getScene().getWindow(); // obtener el Stage actual desde un nodo
                loginStage.close();
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText(null);
                alerta.setContentText("Error Contraseña Incorrecta");
                alerta.showAndWait();
            }
        }
    }
}
