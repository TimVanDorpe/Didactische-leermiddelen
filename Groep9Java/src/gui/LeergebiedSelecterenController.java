/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.ProductController;
import domein.Leergebied;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Jarne
 */
class LeergebiedSelecterenController extends GridPane {

    @FXML
    private Button btnSendRight, btnSendLeft, btnKlaar, btnToevoegenNieuwLeergebied;

    @FXML
    private TextField txtNieuwLeergebied;
    @FXML
    private ListView<String> alleLeergebieden, toegevoegdeLeergebieden;

    public ProductController dc;

    public LeergebiedSelecterenController(ProductController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LeergebiedSelecteren.fxml"));
        this.dc = dc;
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        //leergebied strings
        updateView();

//     dc.getToegevoegd().addListener(
//        (ListChangeListener<String>) e-> btnSendLeft.setDisable(
//                        dc.geenToegevoegd()));
//        
//        dc.getLeergebieden().addListener(
//        (ListChangeListener<String>) e-> btnSendRight.setDisable(
//                        dc.geenLeergebieden()));
    }

    private void updateView() {

        alleLeergebieden.setItems(FXCollections.observableArrayList(dc.getLeergebieden().toString()));
        toegevoegdeLeergebieden.setItems(FXCollections.observableArrayList(dc.getToegevoegdeLeergebieden().toString()));
        toegevoegdeLeergebieden.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    @FXML
    private void sendRight(ActionEvent event) {
        String naam = alleLeergebieden.getSelectionModel().getSelectedItem();
        dc.voegLeergebiedToeBijHuidigProduct(naam);
        if (naam != null) {

            alleLeergebieden.getSelectionModel().clearSelection();

        }
        updateView();

    }

    private void addLeergebied(String naam) {

    }

    //string to leergebied 
    @FXML
    private void sendLeft(ActionEvent event) {

        String naam = toegevoegdeLeergebieden.getSelectionModel().getSelectedItem();
        dc.verwijderLeergebiedHuidigProduct(naam);

        if (naam != null) {
            toegevoegdeLeergebieden.getSelectionModel().clearSelection();
          
        }

//       if ( selectedItem!=null)
//       domeinController.removeHero(selectedItem());
    }
//
//    @FXML
//    private void mouseClickedCandidate(MouseEvent event) {
//        int teller = event.getClickCount();
//        Leergebied leergebied;
//
//        if (teller == 2) {
//            String selectedItem = alleLeergebieden.getSelectionModel().getSelectedItem();
//            if (selectedItem != null) {
//                dc.voegLeergebiedToeString(selectedItem);
//                leergebied = dc.getLeergebiedToegevoegdFromString(selectedItem);
//                dc.voegLeergebiedToe(leergebied);
//            }
//        }
//    }

    @FXML
    private void klaarMetToevoegen(ActionEvent event) {
        Stage stage = (Stage) btnKlaar.getScene().getWindow();
        // do what you have to do

        alleLeergebieden.getItems().clear();
        toegevoegdeLeergebieden.getItems().clear();
        stage.close();
    }

//    private void sluitVenster() {
//       
//        Stage stage = (Stage) getScene().getWindow();
//        stage.setOnCloseRequest((WindowEvent we) -> {
//            System.out.println("Stage is closing");
//            alleLeergebieden.getItems().clear();
//            toegevoegdeLeergebieden.getItems().clear();
//                    stage.close();
//
//        });
//    }
    @FXML
    private void toevoegenNieuwLeergebied(ActionEvent event) {
        String nieuwleergebied = txtNieuwLeergebied.getText();
        
        dc.nieuwLeergebiedToevoegen(nieuwleergebied);
        txtNieuwLeergebied.clear();
        alleLeergebieden.getItems().clear();
        toegevoegdeLeergebieden.getItems().clear();

        updateView();

    }
}
