/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Doelgroep;
import domein.Firma;
import domein.Leergebied;
import domein.Product;
import persistentie.ProductController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
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

public class ProductDetailController extends Pane implements Observer {

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
    private TextField txtFirma, txtPlaats;
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

    private ProductController dc;
    String url = "";
    /**
     * Initializes the controller class.
     */
    final FileChooser fileChooser = new FileChooser();

   
    
    public ProductDetailController(ProductController dc){
        // TODO
       
     FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductDetail.fxml"));
        this.dc = dc;
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
    @FXML
    private void wijzigProduct(ActionEvent event) {

        String naam = txtNaam.getText();
        String omschrijving = txtOmschrijving.getText();
        int artikkelnummer = Integer.parseInt(txtArtikelnummer.getText());
        double prijs = Double.parseDouble(txtPrijs.getText());
        int aantal = Integer.parseInt(txtAantal.getText());
        String plaats = txtPlaats.getText();

        String firmaNaam = txtFirma.getText();
        if(firmaNaam == null){
            firmaNaam = "";
        }
        String firmaEmail = txtEmailFirma.getText();
        if(firmaEmail == null){
            firmaEmail = "";
        }

        Firma firma = new Firma(firmaNaam, firmaEmail);

        String naamDoelgroep = txtDoelgroepen.getText();
        if(naamDoelgroep == null ){
            naamDoelgroep = "";
        }
        Doelgroep doelgroep = new Doelgroep(naamDoelgroep);

        String namenLeergebieden = txtLeergebieden.getText();
        List<Leergebied> leergebieden = new ArrayList<>();
        if(namenLeergebieden == null){
            Leergebied leergebied = new Leergebied("");
            leergebieden.add(leergebied);
        } else {
            Leergebied leergebied = new Leergebied(txtLeergebieden.getText());
            leergebieden.add(leergebied);
        }

        dc.wijzigProduct(getUrl(), naam, omschrijving, artikkelnummer, prijs, aantal, plaats, firma, doelgroep, leergebieden);
        
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
        setUrl(file.getPath());
    }

    //steekt alle gegevens in de textfields
    @Override
    public void update(Observable o, Object arg) {
        Product product = (Product) arg;
        txtAantal.setText(Integer.toString(product.getAantal()));
        txtArtikelnummer.setText(Integer.toString(product.getArtikelnummer()));
        txtFirma.setText(product.getFirma().getNaam());
        txtEmailFirma.setText(product.getFirma().getEmailContactPersoon());
        txtPrijs.setText(Double.toString(product.getPrijs()));
        txtNaam.setText(product.getNaam());
        txtOmschrijving.setText(product.getOmschrijving());
        txtPlaats.setText(product.getPlaats());

    }
}
