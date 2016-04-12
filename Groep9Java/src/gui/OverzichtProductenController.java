/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Product;
import domein.ProductController;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class OverzichtProductenController  extends BorderPane implements Observer{

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
    
     private ProductController domeinController;

    public OverzichtProductenController(ProductController domeinController) {
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

       
        tblProducten.getSelectionModel().selectedItemProperty().addListener((ObservableValue,oldValue,newValue) -> {
            if(newValue!= null){
                domeinController.setGeselecteerdProduct(newValue);
            }
        });

        tblProducten.setItems(domeinController.getProductSortedList());
    }

   
    

    @Override
    public void update(Observable o, Object arg) {
       tblProducten.setItems(domeinController.getProductSortedList());
    }

    
   
}
