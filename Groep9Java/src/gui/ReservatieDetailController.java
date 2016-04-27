/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Product;
import domein.ProductController;
import domein.Reservatie;
import domein.ReservatieController;
import util.Helper;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ReservatieDetailController extends Pane implements Observer {

    private ReservatieController rc;
    private ProductController pc;

    @FXML
    private Button btnWijzigen;

    @FXML
    private Button btnVerwijderen;
    @FXML
    private Label lblError;
    @FXML
    private Label lblEindDatum;
    @FXML
    private Label lblAantal;
    @FXML
    private Label lblStartDatum;
    @FXML
    private TextField txtProduct;
    @FXML
    private TextField txtAantal;
    
    @FXML
    private Label lblProduct;
    @FXML
    private Label lblStudent;
    @FXML
    private TextField txtStudent;
    @FXML
    private ImageView imgViewFoto;
    @FXML
    private Button btnAnnuleer;
    @FXML
    private Button btnLeegmaken;
    @FXML
    private DatePicker dpStartdatum, dpEindDatum;

    private String product, student, startDatum, eindDatum;
    private int aantal;
    private LocalDate startDate, eindDate;

    public ReservatieDetailController(ReservatieController rc, ProductController pc) {
        // TODO

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservatieDetail.fxml"));
        this.rc = rc;
        this.pc = pc;
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        if (rc.getSelectionModelEmpty()) {
            btnAnnuleer.setDisable(true);
            txtAantal.setDisable(true);
          dpEindDatum.setDisable(true);
            dpStartdatum.setDisable(true);
            txtProduct.setDisable(true);
            txtStudent.setDisable(true);
            btnWijzigen.setDisable(true);
            btnLeegmaken.setDisable(true);
            btnVerwijderen.setDisable(true);

        }
    }

    @FXML
    private void wijzigReservatie(ActionEvent event) {

        try {
            //valideerVelden(true);

            /* NA DEMO UIT COMMENTAAR
             this.product = txtProduct.getText();
              this.student = txtStudent.getText();
            this.startDatum = txtStartDatum.getText();
            this.eindDatum = txtEindDatum.getText();
             */
            //DEZE OOK TIJDELIJK VOOR DEMO
            if (txtAantal.getText().equals("") || !Helper.isInteger(txtAantal.getText())) {
                throw new IllegalArgumentException("Aantal moet een getal zijn");
            }

            if (Helper.isInteger(txtAantal.getText()) && (Integer.parseInt(txtAantal.getText()) <= 0)) {
                throw new IllegalArgumentException("Aantal moet positief zijn");
            }
            if (Helper.isInteger(txtAantal.getText()) && (Integer.parseInt(txtAantal.getText()) > 20)) {
                throw new IllegalArgumentException("Aantal kan niet groter zijn dan het totaal beschikbare aantal");
            }

            this.aantal = Integer.parseInt(txtAantal.getText());
            this.student = txtStudent.getText();

            lblError.setText("");

            Product prod = pc.getProductenLijst().stream().filter(p -> p.getNaam().equalsIgnoreCase(txtProduct.getText())).findAny().get();
            
            // dit uit comment na demo
            rc.wijzigReservatie(prod, aantal, student, startDate, eindDate);

            //demo
            rc.wijzigAantal(Integer.parseInt(txtAantal.getText()));

            //TIJDELIJK VOOR DEMO
            lblAantal.setText("Aantal");
            lblAantal.setTextFill(Color.web("#000000"));

        } catch (IllegalArgumentException ex) {

            //TIJDELIJK VOOR DEMO
            lblAantal.setText("Aantal*");
            lblAantal.setTextFill(Color.web("#F20000"));

            lblError.setText(ex.getMessage());
            lblError.setTextFill(Color.web("#F20000"));

        }
    }

    //steekt alle gegevens in de textfields
    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {

            lblError.setText("");
            //maakLabelsTerugNormaal();

            //DEMO TIJDELIJK
            lblAantal.setText("Aantal");
            lblAantal.setTextFill(Color.web("#000000"));

            Reservatie res = (Reservatie) arg;
            txtAantal.setText(Integer.toString(res.getGereserveerdAantal()));

            txtProduct.setText(res.getGereserveerdProduct().getNaam());

            txtStudent.setText(res.getGebruiker());

            
            dpStartdatum.setValue(res.getStartDatum());
           
            dpEindDatum.setValue(res.getEindDatum());

            //alles terug enablen als er iets geselcteerd wordt
            btnAnnuleer.setDisable(false);
            
            txtProduct.setDisable(false);
            txtStudent.setDisable(false);
            btnLeegmaken.setDisable(false);
            dpStartdatum.setDisable(false);
            dpEindDatum.setDisable(false);
            txtAantal.setDisable(false);
            btnWijzigen.setDisable(false);
            btnVerwijderen.setDisable(false);

        }
    }

    @FXML
    private void resetWaarden(ActionEvent event) {

        txtAantal.setText("");
        dpEindDatum.setValue(LocalDate.now());
        dpStartdatum.setValue(LocalDate.now());
        txtProduct.setText("");
        txtStudent.setText("");

        rc.setGeselecteerdeReservatie(null);
        btnWijzigen.setDisable(true);
        btnVerwijderen.setDisable(true);
    }

    @FXML
    private void annuleerWijziging(ActionEvent event) {
        rc.updateDetailvenster();
    }

    @FXML
    private void geefStartDatum(ActionEvent event) {
        startDate = dpStartdatum.getValue();

    }

    @FXML
    private void geefEindDatum(ActionEvent event) {
        eindDate = dpEindDatum.getValue();
    }

    @FXML
    private void verwijderReservatie(ActionEvent event) {
        Stage stage = new Stage();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmatie");
        alert.setHeaderText("Reservatie verwijderen");
        alert.setContentText("U staat op het punt om deze reservatie te verwijderen. Weet u het zeker?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // OK

            rc.removeReservatie();

        } else {
            // Niet OK

            stage.close();

        }

        stage.close();
    }

    /*
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
     */
}
