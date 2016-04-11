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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;


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
    private Button btnToevoegen;
    @FXML
    private Button btnFoto;
    @FXML
    private Button btnAnnuleer;

    private ProductController dc;
    @FXML
    private CheckBox uitleenbaarheid;
    
     final FileChooser fileChooser = new FileChooser();
    @FXML
    private ImageView imgViewFoto;

    /**
     * Initializes the controller class.
     */
    
    public ProductToevoegenController(ProductController dc) {
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
        String naam = txtNaam.getText();
        String omschrijving = txtOmschrijving.getText();
        int artikkelnummer = Integer.parseInt(txtArtikelnummer.getText());
        double prijs = Double.parseDouble(txtPrijs.getText());
        int aantal = Integer.parseInt(txtAantal.getText());
        String plaats = txtPlaats.getText();
        Firma firma = new Firma(txtFirma.getText() , txtEmailFirma.getText());
        Doelgroep doelgroep = new Doelgroep(txtDoelgroepen.getText());
        Leergebied leergebied = new Leergebied(txtLeergebieden.getText());
        Leergebied leergebied2 = new Leergebied(txtLeergebieden.getText());
        List<Leergebied> leergebieden = null;
        leergebieden.add(leergebied);
        leergebieden.add(leergebied2);
        dc.voegProductToe(naam, naam, omschrijving, artikkelnummer, prijs, aantal, plaats, firma, doelgroep, leergebieden);
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
    
    
    
}
