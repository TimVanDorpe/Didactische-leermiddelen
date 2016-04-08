/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Doelgroep;
import domein.Firma;
import domein.Leergebied;
import domein.DomeinController;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class ProductToevoegenController extends Pane {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TextField txtNaam;
    @FXML
    private TextArea txtOmschrijving;
    @FXML
    private TextField txtArtikelnummer;
    @FXML
    private TextField txtPrijs;
    @FXML
    private TextField txtAantal;
    @FXML
    private TextField txtFirma , txtPlaats;
    @FXML
    private TextField txtEmailFirma;
    @FXML
    private TextField txtDoelgroepen;
    @FXML
    private TextField txtLeergebieden;
    @FXML
    private CheckBox cbUitleenbaarheid;
    @FXML
    private ImageView imgViewFoto;
    @FXML
    private Button btnToevoegen;
    @FXML
    private Button btnFoto;
    @FXML
    private Button btnAnnuleer;

    private DomeinController dc;
    
    /**
     * Initializes the controller class.
     */
    
    public ProductToevoegenController(DomeinController dc) {
        // TODO
       
     FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductToevoegen.fxml"));
        this.dc = dc;
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    
    } 
    
    
     @FXML
    private void voegLandToe(ActionEvent event) {
        
        String naam = txtNaam.getText();
        String omschrijving = txtOmschrijving.getText();
        int artikkelnummer = Integer.parseInt(txtArtikelnummer.getText());
        double prijs = Integer.parseInt(txtPrijs.getText());
        int aantal = Integer.parseInt(txtAantal.getText());
        String plaats = txtPlaats.getText();
        Firma firma = new Firma(txtFirma.getText() , txtEmailFirma.getText());
        Doelgroep doelgroep = new Doelgroep(txtDoelgroepen.getText());
        Leergebied leergebied = new Leergebied(txtLeergebieden.getText());
        Leergebied leergebied2 = new Leergebied(txtLeergebieden.getText());
        Collection<Leergebied> leergebieden = null;
        leergebieden.add(leergebied);
        leergebieden.add(leergebied2);
        dc.voegProductToe(naam, naam, omschrijving, artikkelnummer, prijs, aantal, plaats, firma, doelgroep, leergebieden);
    }  
    
    
    
}
