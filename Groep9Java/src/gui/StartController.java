/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.ProductController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Tim
 */
public class StartController extends BorderPane {
  private ProductController dc;

    public StartController(ProductController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Start.fxml"));
        this.dc = dc;
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
   
    
    
    @FXML
    public void productToevoegen() {
        setCenter(new ProductToevoegenController(dc));
    }

    
}
