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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class ProductToevoegenController extends Pane {

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
    private Button btnToevoegen;
    @FXML
    private Button btnFoto;
    @FXML
    private Button btnAnnuleer;

    private DomeinController dc;
    @FXML
    private CheckBox uitleenbaarheid;
    
     final FileChooser fileChooser = new FileChooser();
    @FXML
    private ImageView imgViewFoto;
    @FXML
    private Label lblError;

    /**
     * Initializes the controller class.
     */
    
    public ProductToevoegenController(DomeinController dc){
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
     private void voegProductToe(ActionEvent event) {
         try{
             
        
        //if(txtNaam.getText()!=null || !txtNaam.getText().equals("")){
                    String naam = txtNaam.getText();
        //}
        
        
        String omschrijving = txtOmschrijving.getText();
        
        int artikkelnummer = 0;
        if(txtArtikelnummer.getText() != null || !txtArtikelnummer.getText().equals("")){
           artikkelnummer = Integer.parseInt(txtArtikelnummer.getText());
        }else{
            throw new NumberFormatException("artikelnummer mag niet leeg zijn");
        }
        double prijs = 0.0;
        if(txtPrijs.getText() != null || !txtPrijs.getText().equals("")){
            prijs = Double.parseDouble(txtPrijs.getText());
        }else{
            throw new NumberFormatException("prijs moet een double zijn");
        }
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
        
        
        dc.voegProductToe(naam, naam, omschrijving, artikkelnummer, prijs, aantal, plaats, firma, doelgroep, leergebieden);
         } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fout");
            alert.setContentText("Er deden zich fouten voor, probeer opnieuw (nullpointer)");
            alert.showAndWait();
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fout");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        } catch (IllegalArgumentException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fout");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        } catch(Exception e){
             lblError.setText(e.toString());
             lblError.setTextFill(Color.web("#F20000"));
         }
    }  

   
   
     @FXML
    private void fotoToevoegen(ActionEvent event) {
        File file = fileChooser.showOpenDialog(null);
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG,extFilterPNG);
        
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgViewFoto.setImage(image);
            
        } catch (IOException ex) {
            Logger.getLogger(ProductDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    @FXML
    private void annuleerToevoegen(){
        Stage stage = (Stage) btnAnnuleer.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
    
    
    
}
