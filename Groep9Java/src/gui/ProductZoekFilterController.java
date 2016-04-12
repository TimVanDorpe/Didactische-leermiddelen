/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Doelgroep;
import domein.Firma;
import domein.Leergebied;
import domein.ProductController;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Thomas
 */

 
public class ProductZoekFilterController extends Pane{
private ProductController domeinController;
    @FXML
    private Button btnAnnuleer;
    @FXML
    private Button btnToevoegen;
    @FXML
    private TextField txtNaam;
    @FXML
    private TextField txtArtikelnummer;
    @FXML
    private TextField txtAantal;
    @FXML
    private TextField txtFirma;
    @FXML
    private TextField txtEmailFirma;
    @FXML
    private TextField txtDoelgroepen;
    @FXML
    private TextField txtLeergebieden;
    @FXML
    private CheckBox uitleenbaarheid;
    @FXML
    private TextField txtPlaats;
    @FXML
    private TextField txfVanPrijs;
    @FXML
    private TextField txfTotPrijs;
    @FXML
    private Label lblError;

  

    ProductZoekFilterController(ProductController domeinController) {
        this.domeinController = domeinController;
    }

   

    @FXML
    private void annuleer(ActionEvent event) {
    }

    @FXML
    private void filterProducten(ActionEvent event) {
          try{
        String trefwoord = txtNaam.getText();
      
        
        int artikkelnummer = 0;
        if(txtArtikelnummer.getText() != null || !txtArtikelnummer.getText().equals("")){
            artikkelnummer = Integer.parseInt(txtArtikelnummer.getText());
        }
        double prijs = 0.0;
//        if(txtPrijs.getText() != null || !txtPrijs.getText().equals("")){
//            prijs = Double.parseDouble(txtPrijs.getText());
//        }
        int aantal = 0;
        if(txtAantal.getText() != null || !txtAantal.getText().equals("")){
           aantal = Integer.parseInt(txtAantal.getText());
        }else{
            throw new IllegalArgumentException("Aantal mag niet leeg zijn.");
        }
       
        String plaats = txtPlaats.getText();
        Firma firma = new Firma(txtFirma.getText() , txtEmailFirma.getText());
        Doelgroep doelgroep = new Doelgroep(txtDoelgroepen.getText());
        Leergebied leergebied = new Leergebied(txtLeergebieden.getText());
        Leergebied leergebied2 = new Leergebied(txtLeergebieden.getText());
        List<Leergebied> leergebieden = new ArrayList<>();
        leergebieden.add(leergebied);
        leergebieden.add(leergebied2);
        //dc.voegProductToe(naam, naam, omschrijving, artikkelnummer, prijs, aantal, plaats, firma, doelgroep, leergebieden);
         }catch(Exception e){
             lblError.setText(e.toString());
             lblError.setTextFill(Color.web("#F20000"));
         }
    }

   
}
