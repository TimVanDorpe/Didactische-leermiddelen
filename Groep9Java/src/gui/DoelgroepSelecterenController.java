package gui;

import domein.Doelgroep;
import domein.Leergebied;
import domein.ProductController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jarne
 */
public class DoelgroepSelecterenController extends GridPane {

    @FXML
    private Button btnSendRight;
    @FXML
    private Button btnSendLeft;
    @FXML
    private Button btnKlaar;
    @FXML
    private ListView<String> alleDoelgroepen;
    @FXML
    private ListView<String> toegevoegdeDoelgroepen;

    /**
     * Initializes the controller class.
     */
    public ProductController dc;

    public DoelgroepSelecterenController(ProductController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DoelgroepSelecteren.fxml"));
        this.dc = dc;
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        updateDoelgroepen();
    }

    private void updateDoelgroepen() {

        ObservableList<String> listnieuw = FXCollections.observableArrayList();
        dc.getDoelgroepen().stream().map((l) -> l.getNaam()).forEach((naam) -> {
            listnieuw.add(naam);
        });

        ObservableList<String> listtoegevoegd = FXCollections.observableArrayList();
        if (dc.getHuidigProduct() != null) {
            dc.getToegevoegdeDoelgroepen().stream().map((l) -> l.getNaam()).forEach((naam) -> {
                listtoegevoegd.add(naam);
            });
        }

        alleDoelgroepen.setItems(listnieuw);
        toegevoegdeDoelgroepen.setItems(listtoegevoegd);
        toegevoegdeDoelgroepen.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void sendRight(ActionEvent event) {
        String naam = alleDoelgroepen.getSelectionModel().getSelectedItem();
        dc.voegDoelgroepenToeBijHuidigProduct(naam);
        if (naam != null) {

            alleDoelgroepen.getSelectionModel().clearSelection();

        }
        updateDoelgroepen();

    }

    @FXML
    private void sendLeft(ActionEvent event) {

        String naam = toegevoegdeDoelgroepen.getSelectionModel().getSelectedItem();
        dc.verwijderDoelgroepHuidigProduct(naam);

        if (naam != null) {
            toegevoegdeDoelgroepen.getSelectionModel().clearSelection();

        }

    }

    @FXML
    private void klaar(ActionEvent event) {
        Stage stage = (Stage) btnKlaar.getScene().getWindow();
        // do what you have to do

        alleDoelgroepen.getItems().clear();
        toegevoegdeDoelgroepen.getItems().clear();
        stage.close();
    }

}
