package gui;

import domein.Product;
import domein.ProductController;
import domein.Reservatie;
import domein.ReservatieController;
import util.Helper;
import java.io.IOException;
import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ReservatieDetailController extends Pane {

    private ReservatieController rc;
    private ProductController pc;
    @FXML
    private Button btnWijzigen;
    @FXML
    private Label lblError;
    @FXML
    private Label lblAantal;
    @FXML
    private TextField txtProduct;
    @FXML
    private TextField txtAantal;
    @FXML
    private ComboBox cbMateriaal, cbStudent, cbStudent2;
    @FXML
    private Label lblProduct;
    @FXML
    private Label lblTitel;
    @FXML
    private Button btnAnnuleer, btnToevoegen;
    @FXML
    private DatePicker dpStartdatum, dpEindDatum;
    @FXML
    private Label lblMax;
    private String student;
    private int aantal;
    private LocalDate startDate, eindDate;
    private boolean isWijziging;
    private Reservatie huidigeReservatie;
    private Product huidigProduct;
    @FXML
    private TextField txtOpTeHalen;
    @FXML
    private TextField txtTeruggebracht;
    @FXML
    private Label lblOpTeHalen;
    @FXML
    private Label lblTeruggebracht;

    public ReservatieDetailController(ReservatieController rc, ProductController pc, boolean isWijziging) {
        // TODO

        this.isWijziging = isWijziging;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservatieDetail.fxml"));
        this.rc = rc;
        this.pc = pc;
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        this.huidigeReservatie = rc.getHuidigeReservatie();
        cbStudent.setItems(rc.getStudentenLijst());
        cbStudent2.setItems(rc.getStudentenLijst());
        cbStudent.getSelectionModel().selectFirst();
        cbStudent2.getSelectionModel().clearSelection();

        if (isWijziging) {
            lblProduct.setVisible(false);
            txtProduct.setVisible(false);
            btnToevoegen.setVisible(false);
            cbMateriaal.setVisible(false);
            cbStudent.setVisible(true);
            cbStudent2.setVisible(false);
            lblTeruggebracht.setVisible(true);
            txtTeruggebracht.setVisible(true);
            lblOpTeHalen.setVisible(true);
            txtOpTeHalen.setVisible(true);
            lblTitel.setText("Reservatie wijzigen");

        } else if (!isWijziging) {
            lblTitel.setText("Reservatie toevoegen");
            cbMateriaal.setItems(pc.getStringNaamProducten());
            lblTeruggebracht.setVisible(false);
            txtTeruggebracht.setVisible(false);
            lblOpTeHalen.setVisible(false);
            txtOpTeHalen.setVisible(false);
            cbStudent.setVisible(false);
            cbStudent2.setVisible(true);
            cbMateriaal.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                    huidigProduct = pc.getProductenLijst().stream().filter(p -> p.getNaam().equalsIgnoreCase(t1)).findAny().get();
                    if (dpStartdatum.getValue() != null && dpEindDatum.getValue() != null) {
                        lblMax.setText(String.format("beschikbaar: %d", pc.getProductByNaam(t1).berekenAantalBeschikbaarVoorPeriode(startDate, eindDate)));
                    }
                }
            });
            dpStartdatum.valueProperty().addListener(new ChangeListener<LocalDate>() {
                @Override
                public void changed(ObservableValue<? extends LocalDate> ov, LocalDate d1, LocalDate d2) {
                    startDate = d2;
                    if (huidigProduct != null && dpEindDatum.getValue() != null) {
                        lblMax.setText(String.format("beschikbaar: %d", huidigProduct.berekenAantalBeschikbaarVoorPeriode(startDate, eindDate)));
                    }
                }

            });
            dpEindDatum.valueProperty().addListener(new ChangeListener<LocalDate>() {
                @Override
                public void changed(ObservableValue<? extends LocalDate> ov, LocalDate d1, LocalDate d2) {
                    eindDate = d2;
                    if (huidigProduct != null && dpStartdatum.getValue() != null) {
                        lblMax.setText(String.format("beschikbaar: %d", huidigProduct.berekenAantalBeschikbaarVoorPeriode(startDate, eindDate)));
                    }

                    eindDate = d2;
                    if (huidigProduct != null && dpStartdatum.getValue() != null) {
                        lblMax.setText(String.format("beschikbaar: %d", huidigProduct.berekenAantalBeschikbaarVoorPeriode(startDate, eindDate)));
                    }

                }

            });
        }

        if (huidigeReservatie != null && isWijziging) {

            lblError.setText("");
            lblAantal.setText("Aantal");
            lblAantal.setTextFill(Color.web("#000000"));
            txtAantal.setText(Integer.toString(huidigeReservatie.getGereserveerdAantal()));
            txtOpTeHalen.setText(Integer.toString(huidigeReservatie.getOpTeHalen()));
            txtTeruggebracht.setText(Integer.toString(huidigeReservatie.getTeruggebracht()));

            dpStartdatum.setValue(huidigeReservatie.getStartDatum());
            startDate = huidigeReservatie.getStartDatum();

            dpEindDatum.setValue(huidigeReservatie.getEindDatum());
            eindDate = huidigeReservatie.getEindDatum();

            txtProduct.setText(huidigeReservatie.getGereserveerdProduct().getNaam());
            this.huidigProduct = huidigeReservatie.getGereserveerdProduct();

            lblMax.setText(String.format("beschikbaar: %d", huidigProduct.berekenAantalBeschikbaarVoorPeriode(startDate, eindDate)));

            dpStartdatum.valueProperty().addListener(new ChangeListener<LocalDate>() {
                @Override
                public void changed(ObservableValue<? extends LocalDate> ov, LocalDate d1, LocalDate d2) {
                    lblMax.setText(String.format("beschikbaar: %d", huidigProduct.berekenAantalBeschikbaarVoorPeriode(startDate, eindDate)));
                }

            });
            dpStartdatum.valueProperty().addListener(new ChangeListener<LocalDate>() {
                @Override
                public void changed(ObservableValue<? extends LocalDate> ov, LocalDate d1, LocalDate d2) {
                    lblMax.setText(String.format("beschikbaar: %d", huidigProduct.berekenAantalBeschikbaarVoorPeriode(startDate, eindDate)));
                }

            });

        }

    }

    private void valideerVelden() {

        if (txtAantal.getText().equals("") || !Helper.isInteger(txtAantal.getText())) {
            throw new IllegalArgumentException("Aantal moet een getal zijn");
        }
        if (Helper.isInteger(txtAantal.getText()) && (Integer.parseInt(txtAantal.getText()) <= 0)) {
            throw new IllegalArgumentException("Aantal moet groter dan nul zijn");
        }
        if (Helper.isInteger(txtAantal.getText()) && (Integer.parseInt(txtAantal.getText()) > huidigProduct.berekenAantalBeschikbaarVoorPeriode(startDate, eindDate))) {
            throw new IllegalArgumentException("Aantal kan niet groter zijn dan het totaal beschikbare aantal");
        }
        if (eindDate == null) {
            throw new IllegalArgumentException("Gelieve een einddatum te selecteren");
        }
        if (startDate == null) {
            throw new IllegalArgumentException("Gelieve een startDatum te selecteren");
        }

        if (eindDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Einddatum moet na startdatum komen");
        }

    }

    @FXML
    private void wijzigReservatie(ActionEvent event) {

        try {
            valideerVelden();
            if (cbStudent.getSelectionModel().getSelectedItem() == null) {
                throw new IllegalArgumentException("Gelieve een student te selecteren");
            }
            if (txtOpTeHalen.getText().equals("") || !Helper.isInteger(txtOpTeHalen.getText())) {
                throw new IllegalArgumentException("Op te halen moet een getal zijn");
            }
            if (txtTeruggebracht.getText().equals("") || !Helper.isInteger(txtTeruggebracht.getText())) {
                throw new IllegalArgumentException("Teruggebracht moet een getal zijn");
            }

            if (Helper.isInteger(txtOpTeHalen.getText()) && (Integer.parseInt(txtOpTeHalen.getText()) < 0)) {
                throw new IllegalArgumentException("Op te halen moet positief zijn");
            }
            if (Helper.isInteger(txtTeruggebracht.getText()) && (Integer.parseInt(txtTeruggebracht.getText()) < 0)) {
                throw new IllegalArgumentException("Teruggebracht moet positief zijn");
            }

            if (Helper.isInteger(txtOpTeHalen.getText()) && (Integer.parseInt(txtOpTeHalen.getText()) > rc.getHuidigeReservatie().getGereserveerdAantal())) {
                throw new IllegalArgumentException("Op te halen kan niet groter zijn dan het gereserveerd aantal");
            }

            this.aantal = Integer.parseInt(txtAantal.getText());

            this.student = cbStudent.getSelectionModel().getSelectedItem().toString();

            rc.wijzigReservatie(huidigProduct, aantal, student, startDate, eindDate, Integer.parseInt(txtOpTeHalen.getText()), Integer.parseInt(txtTeruggebracht.getText()));

            Stage stage = (Stage) btnAnnuleer.getScene().getWindow();
            stage.close();
        } catch (IllegalArgumentException ex) {

            lblError.setText(ex.getMessage());
            lblError.setTextFill(Color.web("#F20000"));

        }

    }

    @FXML
    private void resetWaarden(ActionEvent event
    ) {

        txtAantal.setText("");
        dpEindDatum.setValue(LocalDate.now());
        dpStartdatum.setValue(LocalDate.now());
        txtProduct.setText("");
        cbMateriaal.getSelectionModel().clearSelection();
        cbStudent.getSelectionModel().clearSelection();
        rc.setGeselecteerdeReservatie(null);
        btnWijzigen.setDisable(true);
    }

    @FXML
    private void geefStartDatum(ActionEvent event
    ) {
        startDate = dpStartdatum.getValue();

    }

    @FXML
    private void geefEindDatum(ActionEvent event
    ) {
        eindDate = dpEindDatum.getValue();
    }

    @FXML
    private void annuleer(ActionEvent event
    ) {

        Stage stage = (Stage) btnAnnuleer.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void reservatieToevoegen(ActionEvent event) {

        try {
            if (cbStudent2.getSelectionModel().getSelectedItem() == null) {
                throw new IllegalArgumentException("Gelieve een student te selecteren");
            }

            if (cbMateriaal.getSelectionModel().getSelectedItem() == null) {
                throw new IllegalArgumentException("Gelieve een product te selecteren.");
            }
            this.huidigProduct = pc.getProductByNaam(cbMateriaal.getSelectionModel().getSelectedItem().toString());

            valideerVelden();
            this.aantal = Integer.parseInt(txtAantal.getText());

            Reservatie r = new Reservatie(startDate, eindDate, cbStudent2.getSelectionModel().getSelectedItem().toString(), huidigProduct, aantal);
            rc.addReservatie(r);

            Stage stage = (Stage) btnAnnuleer.getScene().getWindow();
            stage.close();

        } catch (IllegalArgumentException ex) {

            lblError.setText(ex.getMessage());
            lblError.setTextFill(Color.web("#F20000"));

        }

    }

}
