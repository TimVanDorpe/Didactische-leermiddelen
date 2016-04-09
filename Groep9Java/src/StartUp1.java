import domein.ProductenBeheer;
import gui.ProductenFrameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class StartUp1 extends Application{
    
     public static void main(String... args) {
        Application.launch(StartUp1.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ProductenBeheer producten = new ProductenBeheer();
        Scene scene = new Scene(new ProductenFrameController(producten));
  
        stage.setScene(scene);
        stage.setTitle("Didactische leermiddelen");

        // The stage will not get smaller than its preferred (initial) size.
        stage.setOnShown(e -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });

        stage.show();
    }
    
}