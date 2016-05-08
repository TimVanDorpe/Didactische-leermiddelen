package gui;

import domein.Product;
import domein.ProductController;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class OverzichtProductenController extends BorderPane implements Observer {

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

    private ProductController dc;
    @FXML
    private Button btnToevoegen;
    @FXML
    private Button btnTrefwoordZoeken;
    @FXML
    private Button btnGeavanceerdZoeken;
    @FXML
    private TextField txtTrefwoord;

    @FXML
    private Button btnBeschikbaarheid;

//        @FXML
//    private Button btnVerwijder;
    public OverzichtProductenController(ProductController domeinController) {
        this.dc = domeinController;
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

        tblProducten.getSelectionModel().selectedItemProperty().addListener((ObservableValue, oldValue, newValue) -> {
            if (oldValue != null) {
                domeinController.setOudProduct(oldValue);
            }
            if (newValue != null) {

                domeinController.setGeselecteerdProduct(newValue);
            }
        });

        tblProducten.setItems(domeinController.getProductSortedList());
        if (tblProducten.getSelectionModel().isEmpty()) {
            dc.setSelectionModelEmpty(true);
            btnBeschikbaarheid.setDisable(true);
        } else {
            dc.setSelectionModelEmpty(false);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        dc.alleProductenOphalen();
        if (arg.equals("maakAllesLeegNaWijziging")) {
            tblProducten.getSelectionModel().clearSelection();
        }
//        tblProducten.getSelectionModel().selectedItemProperty().addListener((ObservableValue, oldValue, newValue) -> {
//            if (oldValue != null) {
//                dc.setOudProduct(oldValue);
//            }
//            if (newValue != null) {
//
//                dc.setGeselecteerdProduct(newValue);
//            }
//        });
        // tblProducten.setItems(dc.getProductSortedList());

    }

    @FXML
    private void zoekOpTrefwoord(ActionEvent event) {

        String trefwoord = txtTrefwoord.getText();
        tblProducten.setItems(dc.zoekOpTrefwoord(trefwoord));

    }

    @FXML
    private void geavanceerdZoeken(ActionEvent event) {
        Stage stage = new Stage();
        stage.setTitle("Geavanceerd zoeken");

        Scene scene = new Scene(new ProductZoekFilterController(dc));
        stage.setScene(scene);

        //this.setDisable(true);
        stage.show();

    }

    @FXML
    private void geefAllesWeer(ActionEvent event) {
        dc.alleProductenOphalen();
        tblProducten.setItems(dc.getProductSortedList());
        tblProducten.getSelectionModel().clearSelection();
    }

    @FXML
    private void enableSelectionModel(MouseEvent event) {
        dc.setSelectionModelEmpty(false);
        btnBeschikbaarheid.setDisable(false);
        tblProducten.setItems(dc.getProductSortedList());
    }

    @FXML
    private void checkBeschikbaarheid(ActionEvent event) {

        Product p = tblProducten.getSelectionModel().getSelectedItem();
        
        Stage stage = new Stage();
        stage.setTitle("Controleer beschikbaarheid");
        Scene scene = new Scene(new CheckBeschikbaarheidController(dc, p));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

}
