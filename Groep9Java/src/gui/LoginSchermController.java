/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Beheerder;
import domein.BeheerderController;
import domein.ProductController;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
    @FXML
    private Label lblError;
    @FXML
    private ImageView imgViewLogo;

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
//          File file = new File("");
//        Image image = new Image(file.toURI().toString());
//        imgViewLogo.setImage(image);
    }
    
    
    @FXML
    private void meldAan(ActionEvent event) {
        
        try{
           
            if(txfGerbuikersNaam.getText() == null || txfWachtwoord.getText() == null){
                throw new IllegalArgumentException("Alle velden moeten ingevuld zijn.");
            }
             beheerderController.meldAan(new Beheerder(txfGerbuikersNaam.getText(), txfWachtwoord.getText()));
         Stage st = (Stage) btnAanmelden.getScene().getWindow();
            st.hide();
             Stage stage = new Stage();
        stage.setTitle("Didactische leermiddelen");

        Scene scene = new Scene(new ProductenFrameController(new ProductController()));
        stage.setScene(scene);

        //this.setDisable(true);
        stage.show();
        
        } catch (IllegalArgumentException ex) {

            lblError.setText(ex.getMessage());
            lblError.setTextFill(Color.web("#F20000"));

        }
       
    }

    @FXML
    private void registreer(ActionEvent event) {
    }

    //wachtwoord vergeten
    @FXML
    private void wachtWoordVersturenNaarGebruiker(ActionEvent event) {
    }
    
}
