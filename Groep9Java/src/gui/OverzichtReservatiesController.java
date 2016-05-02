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
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

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
    private TableColumn<Reservatie, String> clmStatus;
    @FXML
    private Button btnWijzigen;
    @FXML
    private Button btnToevoegen;
    @FXML
    private Button btnAllesWeergeven;
    @FXML
    private ComboBox<String> cmbStatus;

    public OverzichtReservatiesController(ReservatieController rc, ProductController pc) {
        this.rc = rc;
        this.pc =pc;
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
        clmStatus.setCellValueFactory (
                cellData -> cellData.getValue().getStatusProperty());
        tblReservaties.getSelectionModel().selectedItemProperty().addListener((ObservableValue, oldValue, newValue) -> {
            if (newValue != null) {
                rc.setGeselecteerdeReservatie(newValue);
            }
        });
        
        
        
          
        //maak data staat nu hier
        GregorianCalendar startDatum1 = new GregorianCalendar(2016, 3, 6, 8, 0, 0);
        GregorianCalendar eindDatum1 = new GregorianCalendar(2016, 3, 10, 17, 0, 0);
        
        GregorianCalendar startDatum2 = new GregorianCalendar(2016, 3, 13, 8, 0, 0);
        GregorianCalendar eindDatum2 = new GregorianCalendar(2016, 3, 17, 17, 0,0);
        
        
        String gebruiker1 = "student1@hogent.be";
        String gebruiker2 = "student2@hogent.be";
        
     
        
        
        rc.addReservatie(new Reservatie(startDatum1.toZonedDateTime().toLocalDate() , eindDatum1.toZonedDateTime().toLocalDate(), gebruiker1, pc.getProductById(1), 5, 2, 3));
        rc.addReservatie(new Reservatie(startDatum1.toZonedDateTime().toLocalDate(), eindDatum1.toZonedDateTime().toLocalDate(), gebruiker2, pc.getProductById(2), 6 ,0 ,6));
        rc.addReservatie(new Reservatie(startDatum2.toZonedDateTime().toLocalDate(), eindDatum2.toZonedDateTime().toLocalDate(), gebruiker1, pc.getProductById(3), 12 ,12 ,0));
        rc.addReservatie(new Reservatie(startDatum2.toZonedDateTime().toLocalDate(), eindDatum2.toZonedDateTime().toLocalDate(), gebruiker1, pc.getProductById(4), 10 , 5 ,4));
        
        
        
       
        tblReservaties.setItems(rc.getReservatieLijst());
        if (tblReservaties.getSelectionModel().isEmpty()) {
            rc.setSelectionModelEmpty(true);
            //btnVerwijder.setDisable(true);a   
        } else {
            rc.setSelectionModelEmpty(false);
        }
         ObservableList<String> statusLijst = FXCollections.observableArrayList(rc.getReservatieLijst().stream().map(Reservatie-> Reservatie.getStatus()).distinct().collect(Collectors.toList()));
        cmbStatus.setItems(statusLijst);
    }

    @Override
    public void update(Observable o, Object arg) {
     
        
        tblReservaties.setItems(rc.getReservatieLijst());
        // btnVerwijder.setDisable(false);
    }

   
    
     @FXML
    private void ZoekenOpNaam(ActionEvent event) {
        tblReservaties.setItems(rc.zoekOpMateriaalNaam(txtZoeken.getText()));
        
        
        
    }

    @FXML
    private void geefAllesWeer(ActionEvent event) {
    }

    @FXML
    private void wijzigReservatie(ActionEvent event) {
    }

    @FXML
    private void voegReservatieToe(ActionEvent event) {
    }

    @FXML
    private void filterStatus(ActionEvent event) {
        ObservableList<Reservatie> reservatieLijst = FXCollections.observableArrayList();
        for (Reservatie r: rc.getReservatieLijst()){
                    if (r.isNogWeergeven())
                        reservatieLijst.add(r);
            }
        
        tblReservaties.setItems(reservatieLijst);
    }
    
    

}
