
import domein.ProductenBeheer;
import gui.ProductenFrameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class StartUp extends Application {

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new ProductenFrameController(new ProductenBeheer()));
        stage.setScene(scene);
        stage.setTitle("Didactische leermiddelen");
        // The stage will not get smaller than its preferred (initial) size.
        stage.setOnShown((WindowEvent t) -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });
        stage.show();
    }

    public static void main(String... args) {
        Application.launch(StartUp.class, args);
    }
}
