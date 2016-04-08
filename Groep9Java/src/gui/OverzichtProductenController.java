/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Product;
import domein.ProductenBeheer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class OverzichtProductenController  {

    @FXML
    private TableView<Product> tblProducten;
    @FXML
    private TableColumn<Product, String> clmNaam;
    @FXML
    private TableColumn<Product, String> clmOmschrijving;
    @FXML
    private TableColumn<Product, String> clmAantal;
    @FXML
    private TableColumn<Product, String> clmPlaats;
    
     private ProductenBeheer domeinController;

    public OverzichtProductenController(ProductenBeheer domeinController) {
        this.domeinController = domeinController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtProducten.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        clmNaam.setCellValueFactory(
                cellData -> cellData.getValue().naamProperty());
        clmOmschrijving.setCellValueFactory(
                cellData -> cellData.getValue().omschrijvingProperty());

        clmAantal.setCellValueFactory(
                cellData -> cellData.getValue().aantalProperty());
        clmPlaats.setCellValueFactory(
                cellData -> cellData.getValue().plaatsProperty());

        //zonder sorteren
//        tblProducten.getSelectionModel().selectedItemProperty().addListener((ObservableValue,oldValue,newValue) -> {
//            if(newValue!= null){
//                domeinController.setCurrentAuto(newValue);
//            }
//        });

        tblProducten.setItems(domeinController.getProductSortedList());
    }

    
   
}
