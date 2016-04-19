/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.ProductController;
import domein.Leergebied;
import java.io.IOException;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Jarne
 */
class LeergebiedSelecterenController extends GridPane {

    @FXML
    private Button btnSendRight, btnSendLeft, btnKlaar;

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
        alleLeergebieden.setItems(dc.getStringLeergebieden());
        toegevoegdeLeergebieden.setItems(dc.getStringLeergebiedenToegevoegd());
        toegevoegdeLeergebieden.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        dc.getToegevoegd().addListener(
//        (ListChangeListener<String>) e-> btnSendLeft.setDisable(
//                        dc.geenToegevoegd()));
//        
//        dc.getLeergebieden().addListener(
//        (ListChangeListener<String>) e-> btnSendRight.setDisable(
//                        dc.geenLeergebieden()));

    }

    @FXML
    private void sendRight(ActionEvent event) {
        String selectedItem = alleLeergebieden.getSelectionModel().getSelectedItem();
        addHero(selectedItem);
    }

    private void addHero(String naam) {
        Leergebied leergebied = dc.getLeergebiedFromString(naam);
        if (naam != null) {
            alleLeergebieden.getSelectionModel().clearSelection();
            dc.voegLeergebiedToeString(naam);
            dc.voegLeergebiedToe(leergebied);
        }
    }

    //string to leergebied 
    @FXML
    private void sendLeft(ActionEvent event) {

        String selectedItem = toegevoegdeLeergebieden.getSelectionModel().getSelectedItem();
        Leergebied leergebied = dc.getLeergebiedToegevoegdFromString(selectedItem);
        
        if (selectedItem != null) {
            toegevoegdeLeergebieden.getSelectionModel().clearSelection();
            dc.verwijderLeergebiedString(selectedItem);
            dc.verwijderLeergebied(leergebied);//hier geen for each-> die is niet slim genoeg en kan dit niet aan 
        }
//       if ( selectedItem!=null)
//       domeinController.removeHero(selectedItem());

    }

//    @FXML
//    private void mouseClickedCandidate(MouseEvent event) {
//        int teller = event.getClickCount();
//        if (teller == 2) {
//            Leergebied selectedItem = alleLeergebieden.getSelectionModel().getSelectedItem();
//            if (selectedItem != null) {
//                dc.voegLeergebiedToe(selectedItem);
//            }
//        }
//    }
    
    
  
    
    
    @FXML
    private void klaarMetToevoegen(ActionEvent event){
        Stage stage = (Stage) btnKlaar.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
