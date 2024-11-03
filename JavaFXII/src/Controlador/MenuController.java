/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vicen
 */
public class MenuController implements Initializable {

    @FXML
    private Label labelUsuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               labelUsuario.setText(Session.getNombreUsuario());
    }
    @FXML
    public void abrirVentana(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Persona.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Persona");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    @FXML
    private void btnCliente(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Persona.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Persona");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
