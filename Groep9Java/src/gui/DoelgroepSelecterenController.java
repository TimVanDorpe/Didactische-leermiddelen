/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Doelgroep;
import domein.Leergebied;
import domein.ProductController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

        setDoelgroepen();
    }

    private void setDoelgroepen() {
        alleDoelgroepen.setItems(dc.getStringDoelgroepen());
        toegevoegdeDoelgroepen.setItems(dc.getStringToegevoegdeDoelgroepen());
        toegevoegdeDoelgroepen.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void sendRight(ActionEvent event) {
        String selectedItem = alleDoelgroepen.getSelectionModel().getSelectedItem();
        addDoelgroep(selectedItem);

    }

    private void addDoelgroep(String naam) {
        Doelgroep doelgroep = dc.getDoelgroepFromString(naam);
        if (naam != null) {
            alleDoelgroepen.getSelectionModel().clearSelection();
            dc.voegDoelgroepToeString(naam);
            dc.voegDoelgroepToe(doelgroep);

        }
    }

    @FXML
    private void sendLeft(ActionEvent event) {

        String selectedItem = toegevoegdeDoelgroepen.getSelectionModel().getSelectedItem();
        Doelgroep doelgroep = dc.getDoelgroepToegevoegdFromString(selectedItem);

        if (selectedItem != null) {
            toegevoegdeDoelgroepen.getSelectionModel().clearSelection();
            dc.verwijderDoelgroepString(selectedItem);
            dc.verwijderDoelgroep(doelgroep);
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
