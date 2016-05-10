package gui;

import domein.Product;
import domein.ProductController;
import java.io.IOException;
import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CheckBeschikbaarheidController extends Pane {

    private ProductController pc;
    @FXML
    private Label lblMateriaal;
    @FXML
    private Label lblTotaal;
    @FXML
    private Label lblBeschikbaar;
    @FXML
    private Label lblOnbeschikbaar;
    @FXML
    private Label lblUitgeleend, lblError;
    @FXML
    private Button btnSluitVenster;
    @FXML
    private DatePicker dpStart;
    @FXML
    private DatePicker dpEind;

    private LocalDate startDatum, eindDatum;

    private Product geselecteerdProduct;

    public CheckBeschikbaarheidController(ProductController dc, Product p) {
        this.pc = dc;
        this.geselecteerdProduct = p;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckBeschikbaarheid.fxml"));

        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        lblMateriaal.setText(geselecteerdProduct.getNaam());
        lblTotaal.setText(String.format("%d", geselecteerdProduct.getAantal()));

        dpStart.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> ov, LocalDate d1, LocalDate d2) {
                startDatum = d2;
                if (eindDatum == null) {
                    lblBeschikbaar.setText(String.format("%d", geselecteerdProduct.berekenAantalBeschikbaarOpDatum(startDatum)));
                    lblOnbeschikbaar.setText(String.format("%d", geselecteerdProduct.getAantalOnbeschikbaar()));
                    lblUitgeleend.setText(String.format("%d", geselecteerdProduct.berekenAantalUitgeleendOpDatum(startDatum)));
                } else {
                    lblBeschikbaar.setText(String.format("%d", geselecteerdProduct.berekenAantalBeschikbaarVoorPeriode(startDatum, eindDatum)));
                    lblOnbeschikbaar.setText(String.format("%d", geselecteerdProduct.getAantalOnbeschikbaar()));
                    lblUitgeleend.setText(String.format("%d", geselecteerdProduct.berekenAantalUitgeleendVoorPeriode(startDatum, eindDatum)));
                }
                if (eindDatum != null && startDatum != null) {
                    if (eindDatum.isBefore(startDatum)) {
                        lblError.setText("Startdatum moet voor einddatum liggen.");
                        lblError.setTextFill(Color.web("#F20000"));

                    } else {
                        lblError.setText("");
                        lblError.setTextFill(Color.web("#000000"));

                    }
                }
            }
        });
        dpEind.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> ov, LocalDate d1, LocalDate d2) {
                eindDatum = d2;

                if (startDatum == null) {
                    lblBeschikbaar.setText(String.format("%d", geselecteerdProduct.berekenAantalBeschikbaarOpDatum(eindDatum)));
                    lblOnbeschikbaar.setText(String.format("%d", geselecteerdProduct.getAantalOnbeschikbaar()));
                    lblUitgeleend.setText(String.format("%d", geselecteerdProduct.berekenAantalUitgeleendOpDatum(eindDatum)));
                } else {
                    lblBeschikbaar.setText(String.format("%d", geselecteerdProduct.berekenAantalBeschikbaarVoorPeriode(startDatum, eindDatum)));
                    lblOnbeschikbaar.setText(String.format("%d", geselecteerdProduct.getAantalOnbeschikbaar()));
                    lblUitgeleend.setText(String.format("%d", geselecteerdProduct.berekenAantalUitgeleendVoorPeriode(startDatum, eindDatum)));
                }
                if (eindDatum != null && startDatum != null) {
                    if (eindDatum.isBefore(startDatum)) {
                        lblError.setText("Startdatum moet voor einddatum liggen.");
                        lblError.setTextFill(Color.web("#F20000"));

                    } else {
                        lblError.setText("");
                        lblError.setTextFill(Color.web("#000000"));

                    }
                }

            }
        });

    }

    @FXML
    private void sluitVenster(ActionEvent event) {
        Stage stage = (Stage) btnSluitVenster.getScene().getWindow();
        stage.close();
    }

}
