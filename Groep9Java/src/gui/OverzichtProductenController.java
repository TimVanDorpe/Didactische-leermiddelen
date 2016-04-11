/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Product;
import domein.ProductController;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class OverzichtProductenController  extends BorderPane{

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
    @FXML
    private Button btnToevoegen;

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

    @FXML
    private void naarProductPagina() {
        
        //this.setCenter(new ProductToevoegenController(domeinController));
        Stage stage = new Stage();
        stage.setTitle("Product toevoegen");

        Scene scene = new Scene(new ProductToevoegenController(domeinController));
        stage.setScene(scene);

        this.setDisable(true);
        
          
        stage.setOnShown(e -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });

        stage.show();
        
        
        
        

        // Stage stageProductScherm = (Stage) tblProducten.getScene().getWindow();
        
        //Het hoofdvenster mag niet afgesloten worden
        /*
         In that case we can use the consume() function of the WindowEvent. 
         This will consume the event and prevent the window from closing.
         ------------------------------------------------------------------ */
       
        
        
        /*
        
        EventHandler handler = event -> event.consume();
        stageProductScherm.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, handler);

        //het subvenster mag niet gesloten  worden
        //----------------------------------------        
        stage.setOnCloseRequest(handler);

        //luisteraar indien het subscherm gesloten wordt. 
        //---------------------------------------------
        stage.addEventHandler(WindowEvent.WINDOW_HIDING,
                event -> {
                    OverzichtProductenController.this.setDisable(false);
                    tblProducten.setItems((ObservableList<Product>) domeinController.geefOverzichtProducten());
                    stageProductScherm.removeEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, handler);
                });

        // Het subscherm wordt niet kleiner dan het minimum scherm.
        //---------------------------------------------------------

        */
      

        
    }
    
    
}
