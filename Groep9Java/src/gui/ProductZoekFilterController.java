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
import static gui.ProductToevoegenController.isDouble;
import static gui.ProductToevoegenController.isInteger;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Thomas
 */

 
public class ProductZoekFilterController extends Pane{
private DomeinController domeinController;
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

  

    ProductZoekFilterController(DomeinController domeinController) {
        this.domeinController = domeinController;
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductZoekFilter.fxml"));
       
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    

    
   

    @FXML
    private void annuleer(ActionEvent event) {
         Stage stage = (Stage) btnAnnuleer.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    private void filterProducten(ActionEvent event) {
//          try{
        String trefwoord = txtNaam.getText();
      
        
        int artikelnummer = -1;
        if (!txtArtikelnummer.getText().equals("")) {

                if (!isInteger(txtArtikelnummer.getText())) {
                    throw new IllegalArgumentException("artikelnummer moet een getal zijn");
                }

                artikelnummer = Integer.parseInt(txtArtikelnummer.getText());
            }
       
//        if(txtPrijs.getText() != null || !txtPrijs.getText().equals("")){
//            prijs = Double.parseDouble(txtPrijs.getText());
//        }
      
         double vanPrijs = -1;
         double totPrijs = -1;
        if (!txfVanPrijs.getText().equals("") && !txfTotPrijs.getText().equals("")) {
                 if (!isDouble(txfVanPrijs.getText()) && !isDouble(txfTotPrijs.getText())) {
                    throw new IllegalArgumentException("prijs moet een getal zijn");
                }
                vanPrijs = Double.parseDouble(txfVanPrijs.getText());
                totPrijs = Double.parseDouble(txfTotPrijs.getText());
            }
        String plaats = txtPlaats.getText();
        String firma = txtFirma.getText();
        String email = txtEmailFirma.getText();
        String doelgroep = txtDoelgroepen.getText();
        String leergebieden = txtLeergebieden.getText();
     
        domeinController.filterProductLijst(trefwoord, artikelnummer, vanPrijs, totPrijs, plaats, firma,email, doelgroep, leergebieden);
         Stage stage = (Stage) this.getScene().getWindow();
        // do what you have to do
        stage.close();
//         }catch(Exception e){
//             lblError.setText(e.toString());
//             lblError.setTextFill(Color.web("#F20000"));
//         }
    }

   
}
