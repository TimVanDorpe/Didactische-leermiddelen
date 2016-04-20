/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.ProductController;
import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class MenuController extends VBox{

    @FXML
    private Button btnUitloggen;
    @FXML
    private Label lblHuidigeGebruiker;
    @FXML
    private Tab tabCatalogus;
    @FXML
    private Tab tabReservaties;
    @FXML
    private Tab tabBeheerders;
    @FXML
    private Pane paneProducten;
    @FXML
    private Pane paneReservaties;
    @FXML
    private Pane paneBeheerders;

    public MenuController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        ProductenFrameController catalogus = new ProductenFrameController(new ProductController());
        paneProducten.getChildren().add(catalogus);
        
    }
   
 
}
