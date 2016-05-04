/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.ProductController;
import domein.Reservatie;
import domein.ReservatieController;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class OverzichtReservatiesController extends BorderPane implements Observer {

    @FXML
    private TableView<Reservatie> tblReservaties;

    private ReservatieController rc;
    @FXML
    private Button btnZoeken;

    private ProductController pc;
    @FXML
    private TextField txtZoeken;
    @FXML
    private TableColumn<Reservatie, String> clmProduct;
    @FXML
    private TableColumn<Reservatie, String> clmStudent;
    @FXML
    private TableColumn<Reservatie, String> clmStartDatum;
    @FXML
    private TableColumn<Reservatie, String> clmAantal;
    @FXML
    private TableColumn<Reservatie, String> clmEindDatum;
    @FXML
    private TableColumn<Reservatie, String> clmOpTeHalen;
    @FXML
    private TableColumn<Reservatie, String> clmTeruggebracht;
    @FXML
    private TableColumn clmStatus;
    @FXML
    private Button btnWijzigen;
    @FXML
    private Button btnToevoegen;
    @FXML
    private Button btnAllesWeergeven;
    @FXML
    private Button btnVerwijder;
    @FXML
    private ComboBox<String> cmbStatus;

    public OverzichtReservatiesController(ReservatieController rc, ProductController pc) {
        this.rc = rc;
        this.pc = pc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtReservaties.fxml"));

        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        clmProduct.setCellValueFactory(
                cellData -> cellData.getValue().productProperty());

        clmStudent.setCellValueFactory(
                cellData -> cellData.getValue().studentProperty());

        clmAantal.setCellValueFactory(
                cellData -> cellData.getValue().aantalProperty());
        clmStartDatum.setCellValueFactory(
                cellData -> cellData.getValue().startDatumProperty());
        clmEindDatum.setCellValueFactory(
                cellData -> cellData.getValue().eindDatumProperty());

        clmOpTeHalen.setCellValueFactory(
                cellData -> cellData.getValue().opTeHalenProperty());
        clmTeruggebracht.setCellValueFactory(
                cellData -> cellData.getValue().teruggebrachtProperty());
//        clmStatus.setCellValueFactory(
//                cellData -> if(cellData.getValue().)
//                        cellData.getValue().getStatusProperty());
//        
        clmStatus.setCellValueFactory(new PropertyValueFactory<Reservatie, String>("status"));

        // ** The TableCell class has the method setTextFill(Paint p) that you 
        // ** need to override the text color
        //   To obtain the TableCell we need to replace the Default CellFactory 
        //   with one that returns a new TableCell instance, 
        //   and @Override the updateItem(String item, boolean empty) method.
        //
        clmStatus.setCellFactory(new Callback<TableColumn, TableCell>() {

            public TableCell call(TableColumn param) {
                return new TableCell<Reservatie, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {

                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                          
                            // Get fancy and change color based on data
                            if (item.contains("Uitgeleend")) {
                                setStyle("-fx-background-color:#BFBFBF;-fx-color:#000;");
                            }
                            if (item.contains("Klaar te leggen")) {

                                setStyle("-fx-background-color:#FFE945;-fx-color:#000;");

                            }
                            if (item.contains("Niet alles teruggebracht")) {

                                setStyle("-fx-background-color:#FF5F3B;-fx-color:#000;");

                            }
                            if (item.contains("Klaar om op te halen")) {

                                setStyle("-fx-background-color:#BFBFBF;-fx-color:#000;");

                            }
                            setText(item);
                            
                        }else{
                            setText("");
                            setStyle("");
                        }
                    }
                };
            }
        });

        tblReservaties.getSelectionModel().selectedItemProperty().addListener((ObservableValue, oldValue, newValue) -> {
            if (newValue != null) {
                rc.setGeselecteerdeReservatie(newValue);
            }
        });

        //maak data staat nu hier
        GregorianCalendar startDatum1 = new GregorianCalendar(2016, 3, 6, 8, 0, 0);
        GregorianCalendar eindDatum1 = new GregorianCalendar(2016, 3, 10, 17, 0, 0);

        GregorianCalendar startDatum2 = new GregorianCalendar(2016, 3, 13, 8, 0, 0);
        GregorianCalendar eindDatum2 = new GregorianCalendar(2016, 3, 17, 17, 0, 0);

        GregorianCalendar startDatum3 = new GregorianCalendar(2016, 3, 13, 8, 0, 0);
        GregorianCalendar eindDatum3 = new GregorianCalendar(2016, 5, 17, 17, 0, 0);
        
        GregorianCalendar startDatum4 = new GregorianCalendar(2016, 4, 9, 8, 0, 0);
        GregorianCalendar eindDatum4 = new GregorianCalendar(2016, 4, 13, 17, 0, 0);

        String gebruiker1 = "student1@hogent.be";
        String gebruiker2 = "student2@hogent.be";

        rc.addReservatie(new Reservatie(startDatum1.toZonedDateTime().toLocalDate(), eindDatum1.toZonedDateTime().toLocalDate(), gebruiker1, pc.getProductById(1), 5, 2, 3));
        rc.addReservatie(new Reservatie(startDatum1.toZonedDateTime().toLocalDate(), eindDatum1.toZonedDateTime().toLocalDate(), gebruiker2, pc.getProductById(2), 6, 0, 6));
        rc.addReservatie(new Reservatie(startDatum2.toZonedDateTime().toLocalDate(), eindDatum2.toZonedDateTime().toLocalDate(), gebruiker1, pc.getProductById(3), 12, 12, 0));
        rc.addReservatie(new Reservatie(startDatum2.toZonedDateTime().toLocalDate(), eindDatum2.toZonedDateTime().toLocalDate(), gebruiker1, pc.getProductById(4), 10, 5, 4));
        rc.addReservatie(new Reservatie(startDatum3.toZonedDateTime().toLocalDate(), eindDatum3.toZonedDateTime().toLocalDate(), gebruiker2, pc.getProductById(1), 5, 0, 0));
        rc.addReservatie(new Reservatie(startDatum3.toZonedDateTime().toLocalDate(), eindDatum3.toZonedDateTime().toLocalDate(), gebruiker2, pc.getProductById(1), 5, 5, 0));
        rc.addReservatie(new Reservatie(startDatum4.toZonedDateTime().toLocalDate(), eindDatum4.toZonedDateTime().toLocalDate(), gebruiker2, pc.getProductById(1), 5, 0, 0));

        tblReservaties.setItems(rc.getReservatieLijst());
        if (tblReservaties.getSelectionModel().isEmpty()) {
            rc.setSelectionModelEmpty(true);
            btnVerwijder.setDisable(true);
            btnWijzigen.setDisable(true);
        } else {
            rc.setSelectionModelEmpty(false);
        }
        ObservableList<String> statusLijst = FXCollections.observableArrayList(rc.getReservatieLijst().stream().map(Reservatie -> Reservatie.getStatus()).distinct().collect(Collectors.toList()));
        cmbStatus.setItems(statusLijst);
    }

    @Override
    public void update(Observable o, Object arg) {

      //  tblReservaties.setItems(rc.getReservatieLijst());
        btnVerwijder.setDisable(false);
        btnWijzigen.setDisable(false);
    }

    @FXML
    private void ZoekenOpNaam(ActionEvent event) {
        tblReservaties.setItems(rc.zoekOpMateriaalNaam(txtZoeken.getText()));

    }

    @FXML
    private void geefAllesWeer(ActionEvent event) {
        tblReservaties.setItems(rc.getReservatieLijst());
    }

    @FXML
    private void wijzigReservatie(ActionEvent event) {
        Stage stage = new Stage();
        stage.setTitle("Reservatie wijzigen");
        Scene scene = new Scene(new ReservatieDetailController(rc, pc, true)); // isWijziging = true

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void voegReservatieToe(ActionEvent event) {
        Stage stage = new Stage();
        stage.setTitle("Reservatie toevoegen");
        Scene scene = new Scene(new ReservatieDetailController(rc, pc, false)); // isWijziging = false
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void filterStatus(ActionEvent event) {
        ObservableList<Reservatie> reservatieLijst = FXCollections.observableArrayList();
        for (Reservatie r : rc.getReservatieLijst()) {
            if (r.getStatus().equals(cmbStatus.getSelectionModel().getSelectedItem().toString())) {
                reservatieLijst.add(r);
            }
        }
       
        tblReservaties.setItems(reservatieLijst);
    }

    @FXML
    private void verwijderReservatie(ActionEvent event) {
        Stage stage = new Stage();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmatie");
        alert.setHeaderText("Reservatie verwijderen");
        alert.setContentText("U staat op het punt om deze reservatie te verwijderen. Weet u het zeker?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // OK

            rc.removeReservatie();
            tblReservaties.setItems(rc.getReservatieLijst());

        } else {
            // Niet OK

            stage.close();

        }

        stage.close();
    }

}
