/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class ProductToevoegenController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TextField txfNaam;
    @FXML
    private TextArea txfOmschrijving;
    @FXML
    private TextField txfArtikelnummer;
    @FXML
    private TextField txfPrijs;
    @FXML
    private TextField txfAantal;
    @FXML
    private TextField txfFirma;
    @FXML
    private TextField txfEmailFirma;
    @FXML
    private TextField txfDoelgroepen;
    @FXML
    private TextField txfLeergebieden;
    @FXML
    private CheckBox uitleenbaarheid;
    @FXML
    private ImageView imgviewFoto;
    @FXML
    private Button btnToevoegen;
    @FXML
    private Button btnFoto;
    @FXML
    private Button btnAnnuleer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
