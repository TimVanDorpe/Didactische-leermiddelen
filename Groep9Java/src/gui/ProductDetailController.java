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
import domein.DomeinController;
import domein.Helper;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

public class ProductDetailController extends Pane implements Observer {

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
    private ImageView imgViewFoto;
    @FXML
    private Button btnToevoegen;
    @FXML
    private Button btnFoto;
    @FXML
    private Button btnAnnuleer;

    @FXML
    private Label lblError;

    private DomeinController dc;

    final FileChooser fileChooser = new FileChooser();
    @FXML
    private CheckBox uitleenbaarheid;

    public ProductDetailController(DomeinController dc) {
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

        if (dc.getSelectionModelEmpty()) {
            btnToevoegen.setDisable(true);
        btnAnnuleer.setDisable(true);
        btnFoto.setDisable(true); 
        uitleenbaarheid.setDisable(true);
        txtAantal.setDisable(true);
        txtArtikelnummer.setDisable(true);
        txtDoelgroepen.setDisable(true);
        txtEmailFirma.setDisable(true);
        txtFirma.setDisable(true);
        txtLeergebieden.setDisable(true);
        txtNaam.setDisable(true);
        txtOmschrijving.setDisable(true);
        txtPlaats.setDisable(true);
        txtPrijs.setDisable(true);
       
        }
    }

    @FXML
    private void wijzigProduct(ActionEvent event) {

        try {

            /*
            if (event.) {
                throw new UnsupportedClassVersionError("Gelieve eerst een product te selecteren");
            }
             */
            if (txtNaam.getText().equals("")) {
                throw new IllegalArgumentException("naam is verplicht");
            }
            String naam = txtNaam.getText();

            String omschrijving = txtOmschrijving.getText();

            int artikelnummer = 0;

            if (!txtArtikelnummer.getText().equals("")) {

                if (!Helper.isInteger(txtArtikelnummer.getText())) {
                    throw new IllegalArgumentException("artikelnummer moet een getal zijn");
                }

                artikelnummer = Integer.parseInt(txtArtikelnummer.getText());
            }
            double prijs = 0.0;
            if (!txtPrijs.getText().equals("")) {
                if (!Helper.isDouble(txtPrijs.getText())) {
                    throw new IllegalArgumentException("prijs moet een getal zijn");
                }

                prijs = Double.parseDouble(txtPrijs.getText());
            }
            if (txtAantal.getText().equals("")) {
                throw new IllegalArgumentException("aantal is verplicht");
            }

            if (!Helper.isInteger(txtAantal.getText())) {
                throw new IllegalArgumentException("aantal moet een getal zijn");
            }
            int aantal = Integer.parseInt(txtAantal.getText());

            String plaats = txtPlaats.getText();
            String firmaNaam = txtFirma.getText();
            if (firmaNaam == null) {
                firmaNaam = "";
            }
            String firmaEmail = txtEmailFirma.getText();
            if (firmaEmail == null) {
                firmaEmail = "";
            }

            Firma firma = new Firma(firmaNaam, firmaEmail);

            String naamDoelgroep = txtDoelgroepen.getText();
            if (naamDoelgroep == null) {
                naamDoelgroep = "";
            }
            Doelgroep doelgroep = new Doelgroep(naamDoelgroep);
            String namenLeergebieden = txtLeergebieden.getText();
            List<Leergebied> leergebieden = new ArrayList<>();
            if (namenLeergebieden == null) {
                Leergebied leergebied = new Leergebied("");
                leergebieden.add(leergebied);
            } else {
                Leergebied leergebied = new Leergebied(txtLeergebieden.getText());
                leergebieden.add(leergebied);
            }
            lblError.setText(""); // errorlabel clear

            if (imgViewFoto.getImage() == null) {
                dc.wijzigProductZonderFoto(naam, omschrijving, artikelnummer, prijs, aantal, plaats, firma, doelgroep, leergebieden);
            } else {
                dc.wijzigProduct((Blob) imgViewFoto.getImage(), naam, omschrijving, artikelnummer, prijs, aantal, plaats, firma, doelgroep, leergebieden);

            }

        } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fout");
            alert.setContentText("Er deden zich fouten voor, probeer opnieuw (nullpointer)");
            alert.showAndWait();
        } catch (IllegalArgumentException ex) {

            lblError.setText(ex.getMessage());
            lblError.setTextFill(Color.web("#F20000"));

            /*Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fout");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        } catch (Exception e) {
            lblError.setText(e.toString());
            lblError.setTextFill(Color.web("#F20000"));*/
        } catch (UnsupportedClassVersionError ex) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fout");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void fotoToevoegen(ActionEvent event) {
        File file = fileChooser.showOpenDialog(null);
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgViewFoto.setImage(image);

        } catch (IOException ex) {
            Logger.getLogger(ProductDetailController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

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
        
         //alles terug enablen als er iets geselcteerd wordt
        btnToevoegen.setDisable(false);
        btnAnnuleer.setDisable(false);
        btnFoto.setDisable(false); 
        uitleenbaarheid.setDisable(false);
        txtAantal.setDisable(false);
        txtArtikelnummer.setDisable(false);
        txtDoelgroepen.setDisable(false);
        txtEmailFirma.setDisable(false);
        txtFirma.setDisable(false);
        txtLeergebieden.setDisable(false);
        txtNaam.setDisable(false);
        txtOmschrijving.setDisable(false);
        txtPlaats.setDisable(false);
        txtPrijs.setDisable(false);
       
    }

    @FXML
    private void resetWaarden(ActionEvent event) {
        //nog implementen
        txtAantal.setText("");
        txtArtikelnummer.setText("");
        txtDoelgroepen.setText("");
        txtEmailFirma.setText("");
        txtFirma.setText("");
        txtLeergebieden.setText("");
        txtNaam.setText("");
        txtOmschrijving.setText("");
        txtPlaats.setText("");
        txtPrijs.setText("");
        imgViewFoto.setImage(null);
    }

}
