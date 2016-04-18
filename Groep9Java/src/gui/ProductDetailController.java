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
import domein.ProductController;
import util.Helper;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
   // @FXML
   // private TextField txtLeergebieden;
    @FXML
    private ListView<String> listLeergebieden;
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
    @FXML
    private Label lblNaam;
    @FXML
    private Label lblOmschrijving;
    @FXML
    private Label lblArtikelnummer;
    @FXML
    private Label lblPrijs;
    @FXML
    private Label lblAantal;
    @FXML
    private Label lblFirma;
    @FXML
    private Label lblDoelgroepen;
    @FXML
    private Label lblLeergebieden;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblPlaats;

    private ProductController dc;

    final FileChooser fileChooser = new FileChooser();
    @FXML
    private CheckBox uitleenbaarheid;
    @FXML
    private Button btnLeegmaken;
    @FXML
    private Button btnWijzigen;

    String naam, omschrijving, firmaNaam, firmaEmail, plaats, error;
    int artikelnummer, aantal, aantalVerkeerd;
    double prijs;
    @FXML
    private Button btnVerwijderen;

    public ProductDetailController(ProductController dc) {
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
            //txtLeergebieden.setDisable(true);
            txtNaam.setDisable(true);
            txtOmschrijving.setDisable(true);
            txtPlaats.setDisable(true);
            txtPrijs.setDisable(true);

        }
    }

    @FXML
    private void wijzigProduct(ActionEvent event) {

        try {
            valideerVelden();

            /*
             code hieronder van firma, doelgroep leergebieden etc zal alst werkt
             ook in valideerVelden moeten komen volgens mij, eveneens om dan
             ook weer dubbele code bij toevoegen/wijzigen te vermijden (jens)
             */
            Firma firma = new Firma(firmaNaam, firmaEmail);

            String naamDoelgroep = txtDoelgroepen.getText();
            if (naamDoelgroep == null) {
                naamDoelgroep = "";
            }
            Doelgroep doelgroep = new Doelgroep(naamDoelgroep);
            String namenLeergebieden = "";
            ObservableList<Leergebied> leergebieden = FXCollections.observableArrayList();
            if (namenLeergebieden == null) {
                Leergebied leergebied = new Leergebied("");
                leergebieden.add(leergebied);
            } else {
                Leergebied leergebied = new Leergebied("test");
                leergebieden.add(leergebied);
            }
            lblError.setText(""); // errorlabel clear

            if (imgViewFoto.getImage() == null) {
                dc.wijzigProductZonderFoto(naam, omschrijving, artikelnummer, prijs, aantal, plaats, firma, doelgroep, leergebieden);
            } else {
                dc.wijzigProduct(imgViewFoto.getImage().impl_getUrl(), naam, omschrijving, artikelnummer, prijs, aantal, plaats, firma, doelgroep, leergebieden);

            }

        } catch (IllegalArgumentException ex) {

            lblError.setText(ex.getMessage());
            lblError.setTextFill(Color.web("#F20000"));

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
    
    private ObservableList<String> zetLeergebiedenOmNaarString(List<Leergebied> leergebiedenVanProduct){
        ObservableList<String> Stringsleergebieden = FXCollections.observableArrayList(); 
        for (Leergebied l : leergebiedenVanProduct) {
            String naam = l.getNaam();
            Stringsleergebieden.add(naam);
        }
        return Stringsleergebieden;
    }

    //steekt alle gegevens in de textfields
    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {

            lblError.setText("");
            maakLabelsTerugNormaal();

            Product product = (Product) arg;
            txtAantal.setText(Integer.toString(product.getAantal()));
            txtArtikelnummer.setText(Integer.toString(product.getArtikelnummer()));
            txtFirma.setText(product.getFirma().getNaam());
            txtEmailFirma.setText(product.getFirma().getEmailContactPersoon());
            txtPrijs.setText(Double.toString(product.getPrijs()));
            txtNaam.setText(product.getNaam());
            txtOmschrijving.setText(product.getOmschrijving());
            txtPlaats.setText(product.getPlaats());
            listLeergebieden.setItems(zetLeergebiedenOmNaarString(product.getLeergebied()));

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
            //txtLeergebieden.setDisable(false);
            txtNaam.setDisable(false);
            txtOmschrijving.setDisable(false);
            txtPlaats.setDisable(false);
            txtPrijs.setDisable(false);

        }

    }

    @FXML
    private void resetWaarden(ActionEvent event) {
        //nog implementen
        txtAantal.setText("");
        txtArtikelnummer.setText("");
        txtDoelgroepen.setText("");
        txtEmailFirma.setText("");
        txtFirma.setText("");
        //txtLeergebieden.setText("");
        txtNaam.setText("");
        txtOmschrijving.setText("");
        txtPlaats.setText("");
        txtPrijs.setText("");
        imgViewFoto.setImage(null);
    }

    @FXML
    private void annuleerWijziging(ActionEvent event) {
        dc.updateDetailvenster();
    }

    @FXML
    private void voegProductToe(ActionEvent event) {
        try {

            valideerVelden();

            Firma firma = new Firma(txtFirma.getText(), txtEmailFirma.getText());
            //Dit moet zeker weg!!!!
            Doelgroep doelgroep = new Doelgroep(txtDoelgroepen.getText());
            Leergebied leergebied = new Leergebied("test");
            Leergebied leergebied2 = new Leergebied("test");
            List<Leergebied> leergebieden = new ArrayList<>();
            leergebieden.add(leergebied);
            leergebieden.add(leergebied2);
            
            // do what you have to do
            String foto;
            if (imgViewFoto.getImage() == null) {

                dc.voegProductToeZonderFoto(naam, omschrijving, artikelnummer, prijs, aantal, plaats, firma, doelgroep, dc.getLeergebieden());
            } else {
                foto = imgViewFoto.getImage().impl_getUrl();
                dc.voegProductToe(foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats, firma, doelgroep, dc.getLeergebieden());
            }

            lblError.setText(""); // errortekst clearen

        } catch (IllegalArgumentException ex) {

            lblError.setText(ex.getMessage());
            lblError.setTextFill(Color.web("#F20000"));

        }

    }

    private void valideerVelden() {

        maakLabelsTerugNormaal();

        isInputValid();

        //alster meer dan 2 fout zijn algemene message
        if (!isInputValid() && aantalVerkeerd >= 2) {
            throw new IllegalArgumentException("Een aantal velden zijn niet correct ingevuld");
        }

        //specifieke message
        if (!isInputValid()) {
            throw new IllegalArgumentException(error);
        } else {

            this.naam = txtNaam.getText();

            this.omschrijving = txtOmschrijving.getText();

            //dit moet ook nog anders
            this.artikelnummer = 0;

            if (!txtArtikelnummer.getText().equals("")) {

                this.artikelnummer = Integer.parseInt(txtArtikelnummer.getText());
            }
            this.prijs = 0.0;
            if (!txtPrijs.getText().equals("")) {
                this.prijs = Double.parseDouble(txtPrijs.getText());
            }

            this.aantal = Integer.parseInt(txtAantal.getText());

            this.plaats = txtPlaats.getText();

            if (txtFirma.getText() == null) {
                txtFirma.setText("");
            }
            if (txtEmailFirma.getText() == null) {
                txtEmailFirma.setText("");
            }

            if (txtDoelgroepen.getText() == null) {
                txtDoelgroepen.setText("");
            }
            Firma firma = new Firma(firmaNaam, firmaEmail);
            //Dit moet zeker weg!!!!
//            Doelgroep doelgroep = new Doelgroep(txtDoelgroepen.getText());
//            Leergebied leergebied = new Leergebied("test");
//            Leergebied leergebied2 = new Leergebied("test");
//            List<Leergebied> leergebieden = new ArrayList<>();
//            leergebieden.add(leergebied);
//            leergebieden.add(leergebied2);
//
            Blob foto;
            if (imgViewFoto == null) {
                throw new IllegalArgumentException("De foto mag niet leeg zijn !!");
            } else {
                foto = (Blob) imgViewFoto.getImage();
            }

        }
    }

    private void maakLabelsTerugNormaal() {

        lblNaam.setText("Naam*");
        lblNaam.setTextFill(Color.web("#000000"));
        lblArtikelnummer.setText("Artikelnummer vd firma");
        lblArtikelnummer.setTextFill(Color.web("#000000"));
        lblPrijs.setText("Prijs");
        lblPrijs.setTextFill(Color.web("#000000"));
        lblAantal.setText("Aantal*");
        lblAantal.setTextFill(Color.web("#000000"));
    }

    private boolean isInputValid() {

        String message = "";
        boolean c = true;
        int teller = 0;

        if (txtNaam.getText().equals("")) {
            lblNaam.setText("Naam*");
            lblNaam.setTextFill(Color.web("#F20000"));
            teller++;
            c = false;
            message += "Naam is verplicht\n";
        }
        if (!dc.getHuidigProduct().getNaam().equals(txtNaam.getText())) {
            if (dc.isNaamUniek(txtNaam.getText())) {
                lblNaam.setText("Naam*");
                lblNaam.setTextFill(Color.web("#F20000"));
                teller++;
                c = false;
                message += "Naam moet uniek zijn\n";
            }
        }

        if (txtAantal.getText().equals("")) {
            lblAantal.setText("Aantal*");
            lblAantal.setTextFill(Color.web("#F20000"));
            teller++;
            c = false;
            message += "Aantal is verplicht\n";
        }

        if (Helper.isInteger(txtAantal.getText())) {
            if (Integer.parseInt(txtAantal.getText()) < 0) {
                lblAantal.setText("Aantal*");
                lblAantal.setTextFill(Color.web("#F20000"));
                teller++;
                c = false;
                message += "Aantal moet groter zijn dan nul\n";
            }
        } else {

            lblAantal.setText("Aantal*");
            lblAantal.setTextFill(Color.web("#F20000"));
            teller++;
            c = false;
            message += "Aantal moet een getal zijn\n";

        }

        if (!txtArtikelnummer.getText().equals("")) {

            if (!Helper.isInteger(txtArtikelnummer.getText())) {
                lblArtikelnummer.setText("Artikelnummer vd firma*");
                lblArtikelnummer.setTextFill(Color.web("#F20000"));
                teller++;
                c = false;
                message += "Artikelnummer moet een getal zijn\n";
            } else if (Integer.parseInt(txtArtikelnummer.getText()) < 0) {
                lblArtikelnummer.setText("Artikelnummer vd firma*");
                lblArtikelnummer.setTextFill(Color.web("#F20000"));
                teller++;
                c = false;
                message += "Artikelnummer moet groter zijn dan nul\n";
            }
        }

        if (!txtPrijs.getText().equals("")) {
            if (!Helper.isDouble(txtPrijs.getText())) {
                lblPrijs.setText("Prijs*");
                lblPrijs.setTextFill(Color.web("#F20000"));
                teller++;
                c = false;
                message += "Prijs moet een getal zijn\n";
            } else if (Double.parseDouble(txtPrijs.getText()) < 0.0) {
                lblPrijs.setText("Prijs*");
                lblPrijs.setTextFill(Color.web("#F20000"));
                teller++;
                c = false;
                message += "Prijs moet groter zijn dan nul\n";
            }
        }

        aantalVerkeerd = teller;
        error = message;
        return c;

    }

    @FXML
    private void verwijderProduct(ActionEvent event) {
        Stage stage = new Stage();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmatie");
        alert.setHeaderText("Product verwijderen");
        alert.setContentText("U staat op het punt om dit product te verwijderen. Weet u het zeker?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // OK

            dc.verwijderProduct();

        } else {
            // Niet OK

            stage.close();

        }

        stage.close();
    }
}
