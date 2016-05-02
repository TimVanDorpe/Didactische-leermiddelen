/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Product;
import domein.ProductController;
import domein.Reservatie;
import domein.ReservatieController;
import util.Helper;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ReservatieDetailController_old extends Pane implements Observer {

    private ReservatieController rc;
    private ProductController pc;

    @FXML
    private Button btnWijzigen;

    @FXML
    private Button btnVerwijderen;
    @FXML
    private Label lblError;
    @FXML
    private Label lblEindDatum;
    @FXML
    private Label lblAantal;
    @FXML
    private Label lblStartDatum;
    @FXML
    private TextField txtProduct;
    @FXML
    private TextField txtAantal;
    @FXML
    private ComboBox cbMateriaal , cbStudent;
    @FXML
    private Label lblProduct;
    @FXML
    private Label lblStudent;
    @FXML
    private TextField txtStudent;
    @FXML
    private ImageView imgViewFoto;
    @FXML
    private Button btnAnnuleer , btnToevoegen , btnAnnuleerToevoegen;
    @FXML
    private Button btnLeegmaken;
    @FXML
    private DatePicker dpStartdatum, dpEindDatum;

    private String product, student;
    private int aantal;
    private LocalDate startDate, eindDate;

    public ReservatieDetailController_old(ReservatieController rc, ProductController pc) {
        // TODO

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservatieDetail_old.fxml"));
        this.rc = rc;
        this.pc = pc;
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
         btnAnnuleerToevoegen.setVisible(false);
         btnToevoegen.setVisible(false);
         cbMateriaal.setVisible(false);
         cbStudent.setVisible(false);
         cbMateriaal.setItems(pc.getStringNaamProducten());
         cbStudent.setItems(rc.getStudentenLijst());

        if (rc.getSelectionModelEmpty()) {
            btnAnnuleer.setDisable(true);
            txtAantal.setDisable(true);
          dpEindDatum.setDisable(true);
            dpStartdatum.setDisable(true);
            txtProduct.setDisable(true);
            txtStudent.setDisable(true);
            btnWijzigen.setDisable(true);
           
            btnVerwijderen.setDisable(true);
           

        }
    }

    @FXML
    private void wijzigReservatie(ActionEvent event) {

        try {
          
            if (txtAantal.getText().equals("") || !Helper.isInteger(txtAantal.getText())) {
                throw new IllegalArgumentException("Aantal moet een getal zijn");
            }

            if (Helper.isInteger(txtAantal.getText()) && (Integer.parseInt(txtAantal.getText()) <= 0)) {
                throw new IllegalArgumentException("Aantal moet positief zijn");
            }
            if (Helper.isInteger(txtAantal.getText()) && (Integer.parseInt(txtAantal.getText()) > 20)) {
                throw new IllegalArgumentException("Aantal kan niet groter zijn dan het totaal beschikbare aantal");
            }

            this.aantal = Integer.parseInt(txtAantal.getText());
            this.student = txtStudent.getText();
//
//            lblError.setText("");
//
           Product prod = pc.getProductenLijst().stream().filter(p -> p.getNaam().equalsIgnoreCase(txtProduct.getText())).findAny().get();
//            
//            // dit uit comment na demo
            rc.wijzigReservatie(prod, aantal, student, startDate, eindDate, 3 , 8);
//
//            //demo
           rc.wijzigAantal(Integer.parseInt(txtAantal.getText()));

            //TIJDELIJK VOOR DEMO
            lblAantal.setText("Aantal");
            lblAantal.setTextFill(Color.web("#000000"));

        } catch (IllegalArgumentException ex) {

            //TIJDELIJK VOOR DEMO
            lblAantal.setText("Aantal*");
            lblAantal.setTextFill(Color.web("#F20000"));

            lblError.setText(ex.getMessage());
            lblError.setTextFill(Color.web("#F20000"));

        }
    }

    //steekt alle gegevens in de textfields
    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {

            lblError.setText("");
            //maakLabelsTerugNormaal();

            //DEMO TIJDELIJK
            lblAantal.setText("Aantal");
            lblAantal.setTextFill(Color.web("#000000"));

            Reservatie res = (Reservatie) arg;
            txtAantal.setText(Integer.toString(res.getGereserveerdAantal()));

            txtProduct.setText(res.getGereserveerdProduct().getNaam());

            txtStudent.setText(res.getGebruiker());

            
            dpStartdatum.setValue(res.getStartDatum());
           
            dpEindDatum.setValue(res.getEindDatum());

            //alles terug enablen als er iets geselcteerd wordt
            btnAnnuleer.setDisable(false);
            
//            txtProduct.setDisable(false);
//            txtStudent.setDisable(false);
            btnLeegmaken.setDisable(false);
            dpStartdatum.setDisable(false);
            dpEindDatum.setDisable(false);
            txtAantal.setDisable(false);
            btnWijzigen.setDisable(false);
            btnVerwijderen.setDisable(false);

        }
    }

    @FXML
    private void resetWaarden(ActionEvent event) {

        txtAantal.setText("");
        dpEindDatum.setValue(LocalDate.now());
        dpStartdatum.setValue(LocalDate.now());
        txtProduct.setText("");
        txtStudent.setText("");
        cbMateriaal.setItems(null);
        cbStudent.setItems(null);

        rc.setGeselecteerdeReservatie(null);
        btnWijzigen.setDisable(true);
        btnVerwijderen.setDisable(true);
    }

    @FXML
    private void annuleerWijziging(ActionEvent event) {
        rc.updateDetailvenster();
    }

    @FXML
    private void geefStartDatum(ActionEvent event) {
        startDate = dpStartdatum.getValue();

    }

    @FXML
    private void geefEindDatum(ActionEvent event) {
        eindDate = dpEindDatum.getValue();
    }
    
    @FXML
    private void addReservatie(ActionEvent event)
    {
        btnAnnuleerToevoegen.setVisible(true);
        btnToevoegen.setVisible(true);
        btnAnnuleer.setVisible(false);
        btnVerwijderen.setVisible(false);
        btnWijzigen.setVisible(false);
        cbMateriaal.setVisible(true);
         cbStudent.setVisible(true);
         txtProduct.setVisible(false);
         txtStudent.setVisible(false);
         txtAantal.setDisable(false);
         dpEindDatum.setDisable(false);
         dpStartdatum.setDisable(false);
         txtAantal.setText("");
         dpEindDatum.setAccessibleText("");
         dpStartdatum.setAccessibleText("");
        
        
    }
    @FXML
    private void AnnuleerToevoegen(ActionEvent event)
    {
        btnAnnuleerToevoegen.setVisible(false);
        btnToevoegen.setVisible(false);
        btnAnnuleer.setVisible(true);
        btnVerwijderen.setVisible(true);
        btnWijzigen.setVisible(true);
         cbMateriaal.setVisible(false);
         cbStudent.setVisible(false);
          txtProduct.setVisible(true);
         txtStudent.setVisible(true);
    }
    
    
    @FXML
    private void reservatieToevoegen(ActionEvent event)
    {
    Reservatie r = new Reservatie(startDate, eindDate, cbStudent.getSelectionModel().getSelectedItem().toString(), pc.getProductByNaam(cbMateriaal.getSelectionModel().getSelectedItem().toString()), aantal );
     rc.addReservatie(r);
     
     
    
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

        } else {
            // Niet OK

            stage.close();

        }

        stage.close();
    }

}
