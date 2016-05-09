package gui;

import domein.Beheerder;
import domein.BeheerderController;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class OverzichtBeheerdersController extends BorderPane implements Observer {

    @FXML
    private TextField txtZoeken;
    @FXML
    private TableColumn<Beheerder, String> clmNaam;
    @FXML
    private TableColumn<Beheerder, String> clmEmail;
    private BeheerderController bc;
    @FXML
    private TableView<Beheerder> tblBeheerders;

    OverzichtBeheerdersController(BeheerderController bc) {
        this.bc = bc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtBeheerders.fxml"));

        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        clmNaam.setCellValueFactory(
                cellData -> cellData.getValue().naamProperty());

        clmEmail.setCellValueFactory(
                cellData -> cellData.getValue().emailProperty());

        tblBeheerders.getSelectionModel().selectedItemProperty().addListener((ObservableValue, oldValue, newValue) -> {
            if (newValue != null) {
                bc.setGeselecteerdeBeheerder(newValue);
            }
        });
                tblBeheerders.setPlaceholder(new Label("Er zijn geen beheerders om weer te geven."));


        tblBeheerders.setItems(bc.getBeheerderslijst());
        if (tblBeheerders.getSelectionModel().isEmpty()) {
            bc.setSelectionModelEmpty(true);          
        } else {
            bc.setSelectionModelEmpty(false);
        }
    }

    @FXML
    private void zoekenOpNaam(ActionEvent event) {
        tblBeheerders.setItems(bc.zoekenOpBeheerdersNaam(txtZoeken.getText()));
    }

    @Override
    public void update(Observable o, Object arg) {
        tblBeheerders.setItems(bc.getBeheerderslijst());
    }

}
