package gui;

import domein.Firma;
import domein.Product;
import domein.ProductController;
import util.Helper;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;

public class ProductDetailController extends Pane implements Observer/*, Initializable*/ {

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
    private ListView<String> listLeergebieden, listDoelgroepen;
    @FXML
    private ImageView imgViewFoto;
    @FXML
    private Button btnToevoegen;
    @FXML
    private Button btnFoto, btnVoegProductToe;
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
    private Label lblEmail, lblTitelDetail;
    @FXML
    private Label lblPlaats;

    @FXML
    private TextField txtOnbeschikbaar;
    @FXML
    private Label lblOnbeschikbaar;
    @FXML
    private GridPane grid;
    @FXML
    private Region region;
    private ProductController dc;

    final FileChooser fileChooser = new FileChooser();
    @FXML
    private Button btnLeegmaken, btnSelecteerLeergebied, btnSelecteerDoelgroep;
    @FXML
    private Button btnWijzigen, btnToevoegenAnnuleren;

    String naam, omschrijving, firmaNaam, firmaEmail, plaats, error;
    int artikelnummer, aantal, aantalVerkeerd, aantalOnbeschikbaar;
    double prijs;
    URL foto;
    @FXML
    private Button btnVerwijderen;

    private boolean cancelled;
    private boolean wijziging = false;
    private Product huidigProduct;
    private Product oudProduct;

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
            btnWijzigen.setDisable(true);
            btnLeegmaken.setDisable(true);
            btnVerwijderen.setDisable(true);
            btnVoegProductToe.setVisible(false);
            btnToevoegenAnnuleren.setVisible(false);

            grid.setVisible(false);
            lblTitelDetail.setVisible(false);
            btnFoto.setVisible(false);
            btnAnnuleer.setVisible(false);
            btnVerwijderen.setVisible(false);
            btnToevoegenAnnuleren.setVisible(false);
            btnWijzigen.setVisible(false);
            btnLeegmaken.setVisible(false);

            
        }
    }

    @FXML
    private void wijzigProduct(ActionEvent event) {

        try {
            valideerVelden(true);

            Firma firma = new Firma(firmaNaam, firmaEmail);

            lblError.setText(""); // errorlabel clear
            this.wijziging = true;
            dc.wijzigProduct(foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats, firma, aantalOnbeschikbaar);

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

    //steekt alle gegevens in de textfields
    @Override
    public void update(Observable o, Object arg) {

        if (!arg.equals("maakAllesLeegNaWijziging")) {

            grid.setVisible(true);
            lblTitelDetail.setVisible(true);
            btnFoto.setVisible(true);
            btnAnnuleer.setVisible(true);
            btnVerwijderen.setVisible(true);
            btnToevoegenAnnuleren.setVisible(false);
            btnWijzigen.setVisible(true);
            btnLeegmaken.setVisible(true);
            btnVoegProductToe.setVisible(false);
            lblOnbeschikbaar.setVisible(true);
            txtOnbeschikbaar.setVisible(true);

            //binnenkomend product
            dc.setCancelled(false);
            try {
                if (this.wijziging) {
                    resetWaardenprivate();
                    btnWijzigen.setDisable(true);
                    btnLeegmaken.setDisable(true);
                    btnVerwijderen.setDisable(true);
                    btnVoegProductToe.setVisible(false);
                    btnToevoegenAnnuleren.setVisible(false);
                    lblOnbeschikbaar.setVisible(true);
                    txtOnbeschikbaar.setVisible(true);
                    dc.setNieuwHuidigProduct(null);
                } else {
                    //binnenkomend product
                    dc.setCancelled(false);

                    Product product = (Product) arg;

                    // we gaan in deze klasse een huidigProduct moeten maken
                    //eerst controleren of deze null is, zo ja dan wordt het product dat binnen komt het huidigProduct
                    //als deze niet nul is (we hebben dus al een product geselecteerd en klikken nu op een ander)
                    // dan controleren we of de attributen (alles wat in de tekstvelden staat) van ons huidigproduct anders zijn 
                    //dan de attributen van dat product die opgeslaan zijn in de database
                    if (dc.getHuidigProduct() != null) {
                        oudProduct = dc.getProductById(dc.getHuidigProduct().getId());

                        if (!String.format("%s", oudProduct.getAantal()).equals(txtAantal.getText())
                                || !String.format("%s", oudProduct.getArtikelnummer()).equals(txtArtikelnummer.getText())
                                //||  opgeslagenProduct.getDoelgroepen()!= opgeslagenProduct.getDoelgroepen()
                                || !oudProduct.getFirma().getNaam().equalsIgnoreCase(txtFirma.getText())
                                //|| opgeslagenProduct.getFoto() != foto
                                //||  opgeslagenProduct.getLeergebieden()!= huidigProduct.getLeergebieden()
                                || !oudProduct.getNaam().equalsIgnoreCase(txtNaam.getText())
                                || !oudProduct.getOmschrijving().equalsIgnoreCase(txtOmschrijving.getText())
                                || !oudProduct.getPlaats().equalsIgnoreCase(txtPlaats.getText())
                                || !String.format("%s", oudProduct.getAantalOnbeschikbaar()).equals(txtOnbeschikbaar.getText())
                                || !String.format("%.2f", oudProduct.getPrijs()).replace(",", ".").equals(txtPrijs.getText())) {;

                            Stage stage = new Stage();

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmatie");
                            alert.setHeaderText("Niet opgeslagen wijzigingen gevonden");
                            alert.setContentText("OK om ze te verwerpen, Cancel om ze aan te passen");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                // OK wijzigingen verwerpen

                                dc.setCancelled(false);
                                dc.setNieuwHuidigProduct(product);

                                // stage.close();
                            } else {
                                // Niet OK

                                dc.setCancelled(true);
                                // dc.setNieuwHuidigProduct(oudProduct);
                                throw new IllegalArgumentException("Gelieve uw wijzigingen te bevestigen of te annuleren");

                            }

                            stage.close();

                        }
                    }

                    //}
                    if (!dc.getCancelled() || dc.getHuidigProduct() == null) {
                        dc.setNieuwHuidigProduct(product);

                        lblError.setText("");
                        maakLabelsTerugNormaal();

                        // Product product = (Product) arg;
                        txtAantal.setText(Integer.toString(product.getAantal()));
                        txtArtikelnummer.setText(Integer.toString(product.getArtikelnummer()));
                        txtFirma.setText(product.getFirma().getNaam());
                        txtEmailFirma.setText(product.getFirma().getEmailContactPersoon());
                        txtOnbeschikbaar.setText(Integer.toString(product.getAantalOnbeschikbaar()));
                        //String format = String.format("%.2f", product.getPrijs());
                        txtPrijs.setText(String.format("%.2f", product.getPrijs()).replace(",", "."));
                        txtNaam.setText(product.getNaam());
                        txtOmschrijving.setText(product.getOmschrijving());
                        txtPlaats.setText(product.getPlaats());
                        listLeergebieden.setItems(dc.getVoorlopigeLeergebieden());
                        listDoelgroepen.setItems(dc.getVoorlopigeDoelgroepen());
                        //alles terug enablen als er iets geselcteerd wordt
                        btnVerwijderen.setDisable(false);
                        btnAnnuleer.setDisable(false);
                        btnWijzigen.setDisable(false);
                        btnLeegmaken.setDisable(false);

                        if (product.getFoto() != null) {
                            try {
                                BufferedImage img = ImageIO.read(product.getFoto());
                                Image image = SwingFXUtils.toFXImage(img, null);
                                imgViewFoto.setImage(image);
                            } catch (IOException ex) {
                                Logger.getLogger(ProductDetailController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            imgViewFoto.setImage(null);
                        }
                        btnSelecteerDoelgroep.setDisable(false);
                        listDoelgroepen.setDisable(false);

                        /*} else {
            ongewijzigdProductBevestiging();
        }*/
                    }
                }
                dc.setCancelled(false);
                this.wijziging = false;
            } catch (IllegalArgumentException e) {
                lblError.setText(e.getMessage());
            }
        }
    }

    @FXML
    private void resetWaarden(ActionEvent event) {
        resetWaardenprivate();
    }

    private void resetWaardenprivate() {
        //nog implementen
        txtAantal.setText("");
        txtArtikelnummer.setText("");
        txtEmailFirma.setText("");
        txtFirma.setText("");

        txtNaam.setText("");
        txtOmschrijving.setText("");
        txtPlaats.setText("");
        txtPrijs.setText("");
        imgViewFoto.setImage(null);
        //dc.setGeselecteerdProduct(null);
        listLeergebieden.setItems(null);
        listDoelgroepen.setItems(null);
        btnWijzigen.setDisable(true);
        btnVerwijderen.setDisable(true);
        dc.setNieuwHuidigProduct(null);
        txtOnbeschikbaar.setText("");
    }

    @FXML
    private void annuleerWijziging(ActionEvent event) {
        dc.updateDetailvenster();
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

            this.aantalOnbeschikbaar = Integer.parseInt(txtOnbeschikbaar.getText());

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

            if (imgViewFoto.getImage() == null) {

                try {
                    this.foto = new URL("http://i.imgur.com/tsvNPVH.png");
                } catch (MalformedURLException ex) {
                    Logger.getLogger(ProductDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        lblOnbeschikbaar.setText("Aantal onbeschikbaar");
        lblOnbeschikbaar.setTextFill(Color.web("#000000"));
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
        if (!txtOnbeschikbaar.getText().equals("")) {

            if (!Helper.isInteger(txtOnbeschikbaar.getText())) {
                lblOnbeschikbaar.setText("Aantal onbeschikbaar*");
                lblOnbeschikbaar.setTextFill(Color.web("#F20000"));
                teller++;
                c = false;
                message += "Aantal onbeschikbaar moet een getal zijn\n";
            } else if (Integer.parseInt(txtOnbeschikbaar.getText()) < 0) {
                lblOnbeschikbaar.setText("Aantal onbeschikbaar*");
                lblOnbeschikbaar.setTextFill(Color.web("#F20000"));
                teller++;
                c = false;
                message += "Aantal onbeschikbaar moet groter zijn dan nul\n";
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

        this.setDisable(true);

        Stage overZichtStage = (Stage) btnSelecteerLeergebied.getScene().getWindow();
        EventHandler handler = event1 -> event1.consume();
        overZichtStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, handler);

        //het subvenster mag niet gesloten  worden
        //----------------------------------------        
        stage.setOnCloseRequest(handler);

        //luisteraar indien het subscherm gesloten wordt. 
        //---------------------------------------------
        stage.addEventHandler(WindowEvent.WINDOW_HIDING,
                event1 -> {
                    ProductDetailController.this.setDisable(false);
                    listLeergebieden.setItems(dc.getVoorlopigeLeergebieden());
                    overZichtStage.removeEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, handler);
                });

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

    @FXML
    private void zetProductklaarvoortoevoegen(ActionEvent event) {

        grid.setVisible(true);
        lblTitelDetail.setVisible(true);
        btnFoto.setVisible(true);
        btnAnnuleer.setVisible(true);
        btnVerwijderen.setVisible(true);
        btnToevoegenAnnuleren.setVisible(true);
        btnWijzigen.setVisible(true);
        btnLeegmaken.setVisible(true);
        lblOnbeschikbaar.setVisible(false);
        txtOnbeschikbaar.setVisible(false);
        resetWaardenprivate();
        btnVoegProductToe.setVisible(true);
        btnToevoegenAnnuleren.setVisible(true);
        btnAnnuleer.setVisible(false);
        btnLeegmaken.setVisible(false);
        btnWijzigen.setVisible(false);
        btnVerwijderen.setVisible(false);
        Product newProduct = new Product();
        txtNaam.setPromptText("Naam van het nieuwe materiaal");
        lblTitelDetail.setText("Materiaal toevoegen");

    }

    @FXML
    private void voegProductToe(ActionEvent event) {
        try {

            valideerVelden(false);

            Firma firma = new Firma(txtFirma.getText(), txtEmailFirma.getText());

            dc.voegProductToe(foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats, firma);
            //inputChanged = false;

            lblError.setText(""); // errortekst clearen
            btnVoegProductToe.setVisible(false);
            btnToevoegenAnnuleren.setVisible(false);
            btnAnnuleer.setVisible(true);
            btnLeegmaken.setVisible(true);
            btnWijzigen.setVisible(true);
            btnVerwijderen.setVisible(true);
            lblTitelDetail.setText("Details Materiaal");

            //dc.setNieuwHuidigProduct(null);
            //dc.setOudProduct(null);
        } catch (IllegalArgumentException ex) {

            lblError.setText(ex.getMessage());
            lblError.setTextFill(Color.web("#F20000"));

        }

    }

    @FXML
    private void toevoegenAnnuleren(ActionEvent event) {

        btnVoegProductToe.setVisible(false);
        btnToevoegenAnnuleren.setVisible(false);
        btnAnnuleer.setVisible(true);
        btnLeegmaken.setVisible(true);
        btnWijzigen.setVisible(true);
        btnVerwijderen.setVisible(true);
        lblTitelDetail.setText("Details Materiaal");
        resetWaardenprivate();
        lblOnbeschikbaar.setVisible(true);
        txtOnbeschikbaar.setVisible(true);
        grid.setVisible(false);
        lblTitelDetail.setVisible(false);
        btnFoto.setVisible(false);
        btnAnnuleer.setVisible(false);
        btnVerwijderen.setVisible(false);
        btnToevoegenAnnuleren.setVisible(false);
        btnWijzigen.setVisible(false);
        btnLeegmaken.setVisible(false);
        dc.setNieuwHuidigProduct(null);
    }

}
