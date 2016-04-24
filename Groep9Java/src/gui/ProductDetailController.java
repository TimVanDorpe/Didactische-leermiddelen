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
import java.net.MalformedURLException;
import java.net.URL;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    // @FXML
    // private TextField txtLeergebieden;
    @FXML
    private ListView<String> listLeergebieden, listDoelgroepen;
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
    private Button btnLeegmaken, btnSelecteerLeergebied, btnSelecteerDoelgroep;
    @FXML
    private Button btnWijzigen;

    String naam, omschrijving, firmaNaam, firmaEmail, plaats, error;
    int artikelnummer, aantal, aantalVerkeerd;
    double prijs;
    URL foto;
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
            //btnToevoegen.setDisable(true);
            //btnAnnuleer.setDisable(true);
            //btnFoto.setDisable(true);
            //uitleenbaarheid.setDisable(true);
            //txtAantal.setDisable(true);
            //txtArtikelnummer.setDisable(true);
            //txtDoelgroepen.setDisable(true);
            //txtEmailFirma.setDisable(true);
            //txtFirma.setDisable(true);
            //txtLeergebieden.setDisable(true);
            //txtNaam.setDisable(true);
            //txtOmschrijving.setDisable(true);
            //txtPlaats.setDisable(true);
            //txtPrijs.setDisable(true);
            btnWijzigen.setDisable(true);
            btnLeegmaken.setDisable(true);
            btnVerwijderen.setDisable(true);

            //btnSelecteerLeergebied.setDisable(true);
            //listLeergebieden.setDisable(true);
        }
    }

    @FXML
    private void wijzigProduct(ActionEvent event) {

        try {
            valideerVelden(true);

            /*
             code hieronder van firma, doelgroep leergebieden etc zal alst werkt
             ook in valideerVelden moeten komen volgens mij, eveneens om dan
             ook weer dubbele code bij toevoegen/wijzigen te vermijden (jens)
             */
            Firma firma = new Firma(firmaNaam, firmaEmail);
//
//            String naamDoelgroep = txtDoelgroepen.getText();
//            if (naamDoelgroep == null) {
//                naamDoelgroep = "";
//            }
//            Doelgroep doelgroep = new Doelgroep(naamDoelgroep);
            lblError.setText(""); // errorlabel clear

//            if (imgViewFoto.getImage() == null) {
//                dc.wijzigProductZonderFoto(naam, omschrijving, artikelnummer, prijs, aantal, plaats, firma, dc.getListToegevoegdeDoelgroepen(), dc.getListToegevoegdeLeergebieden());
//            } else {
//                dc.wijzigProduct(foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats, firma, dc.getListToegevoegdeDoelgroepen(), dc.getListToegevoegdeLeergebieden());
//
//            }
  dc.wijzigProduct(foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats, firma, dc.getListToegevoegdeDoelgroepen(), dc.getListToegevoegdeLeergebieden());

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
            foto = file.toURL();

        } catch (IOException ex) {
            Logger.getLogger(ProductDetailController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    private ObservableList<String> zetLeergebiedenOmNaarString(List<Leergebied> leergebiedenVanProduct) {
        ObservableList<String> Stringsleergebieden = FXCollections.observableArrayList();
        leergebiedenVanProduct.stream().map((l) -> l.getNaam()).forEach((naam) -> {
            Stringsleergebieden.add(naam);
        });
        return Stringsleergebieden;
    }

    private ObservableList<String> zetDoelgroepenOmNaarString(List<Doelgroep> doelgroepenVanProduct) {
        ObservableList<String> Stringsleergebieden = FXCollections.observableArrayList();
        doelgroepenVanProduct.stream().map((l) -> l.getNaam()).forEach((naam) -> {
            Stringsleergebieden.add(naam);
        });
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
            listDoelgroepen.setItems(zetDoelgroepenOmNaarString(product.getDoelgroep()));
            //alles terug enablen als er iets geselcteerd wordt
            btnToevoegen.setDisable(false);
            btnAnnuleer.setDisable(false);
            btnFoto.setDisable(false);
            btnWijzigen.setDisable(false);
            uitleenbaarheid.setDisable(false);
            txtAantal.setDisable(false);
            txtArtikelnummer.setDisable(false);
            txtEmailFirma.setDisable(false);
            txtFirma.setDisable(false);
            txtNaam.setDisable(false);
            txtOmschrijving.setDisable(false);
            txtPlaats.setDisable(false);
            txtPrijs.setDisable(false);
            btnWijzigen.setDisable(false);
            btnLeegmaken.setDisable(false);
            btnVerwijderen.setDisable(false);
            btnSelecteerLeergebied.setDisable(false);
            listLeergebieden.setDisable(false);
            if(product.getFoto() != null){
             try {
                BufferedImage img = ImageIO.read(product.getFoto());
                 Image image = SwingFXUtils.toFXImage(img, null);
                imgViewFoto.setImage(image);
            } catch (IOException ex) {
                Logger.getLogger(ProductDetailController.class.getName()).log(Level.SEVERE, null, ex);
            }}
            else
            {
            imgViewFoto.setImage(null);
            }
            btnSelecteerDoelgroep.setDisable(false);
            listDoelgroepen.setDisable(false);

        }

    }

    @FXML
    private void resetWaarden(ActionEvent event) {
        //nog implementen
        txtAantal.setText("");
        txtArtikelnummer.setText("");
        txtEmailFirma.setText("");
        txtFirma.setText("");
        //txtLeergebieden.setText("");
        txtNaam.setText("");
        txtOmschrijving.setText("");
        txtPlaats.setText("");
        txtPrijs.setText("");
        imgViewFoto.setImage(null);
        dc.setGeselecteerdProduct(null);
        listLeergebieden.setItems(null);
        listDoelgroepen.setItems(null);
        btnWijzigen.setDisable(true);
        btnVerwijderen.setDisable(true);
    }

    @FXML
    private void annuleerWijziging(ActionEvent event) {
        dc.updateDetailvenster();
    }

    @FXML
    private void voegProductToe(ActionEvent event) {
        try {

            valideerVelden(false);

            Firma firma = new Firma(txtFirma.getText(), txtEmailFirma.getText());
            //Dit moet zeker weg!!!!
//            Doelgroep doelgroep = new Doelgroep(txtDoelgroepen.getText());
            Leergebied leergebied = new Leergebied("test");
            Leergebied leergebied2 = new Leergebied("test");
            List<Leergebied> leergebieden = new ArrayList<>();
            leergebieden.add(leergebied);
            leergebieden.add(leergebied2);

            // do what you have to do
            
//            if (imgViewFoto.getImage() == null) {
//
//                dc.voegProductToeZonderFoto(naam, omschrijving, artikelnummer, prijs, aantal, plaats, firma, dc.getListToegevoegdeDoelgroepen(), dc.getListToegevoegdeLeergebieden());
//            } else {                
//                dc.voegProductToe(foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats, firma, dc.getListToegevoegdeDoelgroepen(), dc.getListToegevoegdeLeergebieden());
//            }
            dc.voegProductToe(foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats, firma, dc.getListToegevoegdeDoelgroepen(), dc.getListToegevoegdeLeergebieden());
          
            lblError.setText(""); // errortekst clearen

        } catch (IllegalArgumentException ex) {

            lblError.setText(ex.getMessage());
            lblError.setTextFill(Color.web("#F20000"));

        }

    }

    private void valideerVelden(boolean isWijziging) {

        maakLabelsTerugNormaal();

        isInputValid(isWijziging);

        //alster meer dan 2 fout zijn algemene message
        if (!isInputValid(isWijziging) && aantalVerkeerd >= 2) {
            throw new IllegalArgumentException("Een aantal velden zijn niet correct ingevuld");
        }

        //specifieke message
        if (!isInputValid(isWijziging)) {
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
            this.firmaNaam = txtFirma.getText();
            if (txtEmailFirma.getText() == null) {
                txtEmailFirma.setText("");
            }
            this.firmaEmail = txtEmailFirma.getText();

            if(imgViewFoto.getImage() == null){
              
                try {
                    this.foto = new URL("http://i.imgur.com/tsvNPVH.png");
                } catch (MalformedURLException ex) {
                    Logger.getLogger(ProductDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (txtDoelgroepen.getText() == null) {
//                txtDoelgroepen.setText("");
//            }
           // Firma firma = new Firma(firmaNaam, firmaEmail);
            //Dit moet zeker weg!!!!
//            Doelgroep doelgroep = new Doelgroep(txtDoelgroepen.getText());
//            Leergebied leergebied = new Leergebied("test");
//            Leergebied leergebied2 = new Leergebied("test");
//            List<Leergebied> leergebieden = new ArrayList<>();
//            leergebieden.add(leergebied);
//            leergebieden.add(leergebied2);
//
            

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

    private boolean isInputValid(boolean isWijziging) {

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

        if (!dc.isNaamUniek(txtNaam.getText(), isWijziging)) {
            lblNaam.setText("Naam*");
            lblNaam.setTextFill(Color.web("#F20000"));
            teller++;
            c = false;
            message += "Naam moet uniek zijn\n";
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

    @FXML
    private void selecteerLeergebieden(ActionEvent event) {

        Stage stage = new Stage();
        stage.setTitle("Leergebied Selecteren");

        Scene scene = new Scene(new LeergebiedSelecterenController(dc));
        stage.setScene(scene);

        //this.setDisable(true);
        stage.show();

    }

    @FXML
    private void selecteerDoelgroepen(ActionEvent event) {

        Stage stage = new Stage();
        stage.setTitle("Doelgroep Selecteren");

        Scene scene = new Scene(new DoelgroepSelecterenController(dc));
        stage.setScene(scene);

        //this.setDisable(true);
        stage.show();

    }
}
