
import domein.DomeinController;
import gui.ProductToevoegenController;
import gui.StartController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tim
 */
public class StartUp extends Application{
    
    
    private DomeinController dc = new DomeinController();

    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(new StartController(dc));
        stage.setScene(scene);
        stage.setTitle("Start");
        stage.show();
    }
    
    public static void main(String... args) {
        Application.launch(StartUp.class, args);
    }
    
}
