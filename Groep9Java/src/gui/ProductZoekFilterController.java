package gui;

import domein.ProductController;
import util.Helper;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class ProductZoekFilterController extends Pane {

    private ProductController domeinController;
    @FXML
    private Button btnAnnuleer;
    @FXML
    private TextField txtNaam;
    @FXML
    private TextField txtArtikelnummer;
    @FXML
    private TextField txtFirma;
    @FXML
    private TextField txtEmailFirma;
    @FXML
    private TextField txtDoelgroepen;
    @FXML
    private TextField txtLeergebieden;
    @FXML
    private TextField txtPlaats;
    @FXML
    private TextField txfVanPrijs;
    @FXML
    private TextField txfTotPrijs;
    @FXML
            private Label lblDoelgroepen;
    @FXML
            private Label lblLeergebieden;

    ProductZoekFilterController(ProductController domeinController) {

        this.domeinController = domeinController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductZoekFilter.fxml"));

        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        txtDoelgroepen.setVisible(false);
        txtLeergebieden.setVisible(false);
        lblDoelgroepen.setVisible(false);
        lblLeergebieden.setVisible(false);
    }

    @FXML
    private void annuleer(ActionEvent event) {
        Stage stage = (Stage) btnAnnuleer.getScene().getWindow();

        stage.close();
    }

    @FXML
    private void filterProducten(ActionEvent event) {

        String trefwoord = txtNaam.getText();
        Stage stage = (Stage) btnAnnuleer.getScene().getWindow();

        int artikelnummer = -1;
        if (!txtArtikelnummer.getText().equals("")) {

            if (!Helper.isInteger(txtArtikelnummer.getText())) {
                throw new IllegalArgumentException("artikelnummer moet een getal zijn");
            }

            artikelnummer = Integer.parseInt(txtArtikelnummer.getText());
        }

        double vanPrijs = -1;
        double totPrijs = -1;
        if (!txfVanPrijs.getText().equals("") && !txfTotPrijs.getText().equals("")) {
            if (!Helper.isDouble(txfVanPrijs.getText()) && !Helper.isDouble(txfTotPrijs.getText())) {
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

        domeinController.filterProductLijst(trefwoord, artikelnummer, vanPrijs, totPrijs, plaats, firma, email, doelgroep, leergebieden);

        stage.close();

    }

}
