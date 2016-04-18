/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Beheerder;
import domein.BeheerderController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class LoginSchermController extends Pane {

    @FXML
    private TextField txfGerbuikersNaam;
    @FXML
    private TextField txfWachtwoord;
    @FXML
    private Button btnAanmelden;
    @FXML
    private Button btnRegistreren;
    @FXML
    private Button btnWachtwoordVergeten;

    /**
     * Initializes the controller class.
     */
     

    private BeheerderController beheerderController;

    public LoginSchermController(BeheerderController beheerderController) {
        this.beheerderController = beheerderController;
         FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScherm.fxml"));
        
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    
    @FXML
    private void meldAan(ActionEvent event) {
        beheerderController.meldAan(new Beheerder(txfGerbuikersNaam.getText(), txfWachtwoord.getText()));
        
    }

    @FXML
    private void registreer(ActionEvent event) {
    }

    @FXML
    private void wachtWoordVersturenNaarGebruiker(ActionEvent event) {
    }
    
}
