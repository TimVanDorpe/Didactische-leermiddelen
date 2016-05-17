package gui;

import domein.BeheerderController;
import domein.ProductController;
import domein.Reservatie;
import domein.ReservatieController;
import java.io.IOException;
import java.util.GregorianCalendar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class MenuController extends VBox {

    @FXML
    private Button btnUitloggen;
    @FXML
    private Label lblHuidigeGebruiker;
    @FXML
    private Pane paneProducten;
    @FXML
    private Pane paneReservaties;
    @FXML
    private Pane paneBeheerders;

    private ProductController pc;
    private ReservatieController rc;
    private BeheerderController beheerderController;

    public MenuController(BeheerderController beheerderController, ProductController pc, ReservatieController rc) {
        this.beheerderController = beheerderController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        this.pc = pc;
        this.rc = rc;

        ProductenFrameController catalogus = new ProductenFrameController(pc);
        paneProducten.getChildren().add(catalogus);

        ReservatiesFrameController reservaties = new ReservatiesFrameController(rc, pc);
        BeheerdersFrameController beheerders = new BeheerdersFrameController(beheerderController);

        paneReservaties.getChildren().add(reservaties);
        paneBeheerders.getChildren().add(beheerders);
        lblHuidigeGebruiker.setText(beheerderController.getAangemeldeBeheerder().getEmail());
    }

    @FXML
    public void toonCatalogus() {
    }

    @FXML
    public void toonReservaties() {
    }

    @FXML
    public void toonBeheerders() {
    }

    @FXML
    private void logUit(ActionEvent event) {
        beheerderController.logUit(); // MOOI

        

        Stage stage = (Stage) btnUitloggen.getScene().getWindow();
        Scene scene = new Scene(new LoginSchermController(beheerderController, pc, rc));
        stage.setScene(scene);
        stage.setTitle("Didactische leermiddelen :  Login");
        stage.setOnShown((WindowEvent t) -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });
        stage.setResizable(false);
        stage.show();

    }

}
