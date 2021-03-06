package gui;

import domein.Beheerder;
import domein.BeheerderController;
import domein.ProductController;
import domein.ReservatieController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginSchermController extends Pane {

    @FXML
    private TextField txfGerbuikersNaam;
    @FXML
    private PasswordField txfWachtwoord;
    @FXML
    private Button btnAanmelden;
    private BeheerderController beheerderController;
    @FXML
    private Label lblError;    
    
    private ProductController pc;
    private ReservatieController rc;

    public LoginSchermController(BeheerderController beheerderController, ProductController pc, ReservatieController rc) {
        this.beheerderController = beheerderController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScherm.fxml"));
        
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
        this.pc = pc;
        this.rc = rc;
        
    }
    

    @FXML
    private void meldAan(ActionEvent event) {

        try {

            if (txfGerbuikersNaam.getText() == null || txfWachtwoord.getText() == null) {
                throw new IllegalArgumentException("Alle velden moeten ingevuld zijn.");
            }
            beheerderController.meldAan(new Beheerder(txfGerbuikersNaam.getText(), txfWachtwoord.getText()));
            Stage st = (Stage) btnAanmelden.getScene().getWindow();
            st.hide();
            Stage stage = new Stage();
            stage.setTitle("Didactische leermiddelen");
            Scene scene = new Scene(new MenuController(beheerderController , pc , rc));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (IllegalArgumentException ex) {

            lblError.setText(ex.getMessage());
            lblError.setTextFill(Color.web("#F20000"));

        }

    }

}
