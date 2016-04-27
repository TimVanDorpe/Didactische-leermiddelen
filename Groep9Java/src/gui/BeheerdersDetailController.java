/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Beheerder;
import domein.BeheerderController;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jarne
 */
public class BeheerdersDetailController extends Pane implements Observer {

    @FXML
    private TextField txtNaam;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtWachtwoord;
    @FXML
    private Button btnNieuweBeheerder;
    @FXML
    private Button btnBeheerderWijzigen;
    @FXML
    private Button btnAnnuleren;
    @FXML
    private Button btnSelectieVerwijderen;
    @FXML
    private Button btnBeheerderVerwijderen;

    @FXML
    private Label lblError;

    private BeheerderController bc;

    BeheerdersDetailController(BeheerderController bc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BeheerdersDetail.fxml"));
        this.bc = bc;

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        if (bc.getSelectionModelEmpty()) {
            btnAnnuleren.setDisable(true);
            btnBeheerderWijzigen.setDisable(true);
            btnSelectieVerwijderen.setDisable(true);
            btnBeheerderVerwijderen.setDisable(true);
            txtEmail.setDisable(true);
            txtNaam.setDisable(true);

        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {

            lblError.setText("");
            //maakLabelsTerugNormaal();

            //DEMO TIJDELIJK
            Beheerder beh = (Beheerder) arg;
            txtNaam.setText(beh.getNaam());
            txtEmail.setText(beh.getEmail());
            txtWachtwoord.setText(beh.getWachtwoord());

            //alles terug enablen als er iets geselcteerd wordt
            btnAnnuleren.setDisable(true);
            btnBeheerderWijzigen.setDisable(true);
            btnSelectieVerwijderen.setDisable(true);
            btnBeheerderVerwijderen.setDisable(false);
            txtEmail.setDisable(false);
            txtNaam.setDisable(false);
        }
    }

    @FXML
    private void wijzigBeheerder(ActionEvent event) {
        try {
            String naam = txtNaam.getText();
            String email = txtEmail.getText();
            String wachtwoord = txtWachtwoord.getText();

            lblError.setText("");

            Beheerder beh = bc.getBeheerderslijst().stream().filter(p -> p.getNaam().equalsIgnoreCase(txtNaam.getText())).findAny().get();

            // dit uit comment na demo
            bc.wijzigBeheerder(naam, email, wachtwoord);

        } catch (IllegalArgumentException ex) {

            lblError.setText(ex.getMessage());
            lblError.setTextFill(Color.web("#F20000"));

        }
    }

    @FXML
    private void annuleerWijziging(ActionEvent event) {
        bc.updateDetailVenster();
    }

    @FXML
    private void resetWaarden(ActionEvent event) {
         txtNaam.setText("");
    
        txtEmail.setText("");
        txtWachtwoord.setText("");

        bc.setGeselecteerdeBeheerder(null);
        btnBeheerderWijzigen.setDisable(true);
        btnBeheerderVerwijderen.setDisable(true);
    }

    @FXML
    private void verwijderReservatie(ActionEvent event) {
        Stage stage = new Stage();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmatie");
        alert.setHeaderText("Beheerder verwijderen");
        alert.setContentText("U staat op het punt om deze beheerder te verwijderen. Weet u het zeker?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // OK

            bc.removeBeheerder();

        } else {
            // Niet OK

            stage.close();

        }

        stage.close();
    }

    //    public void voegNieuweBeheerderToe() {
//        try {
//
//            URL url = new URL("https://studservice.hogent.be/auth/");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setDoOutput(true);
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json");
//
//            //hier uw user id + gehashed password-> password hebben we niet 
//            //dus mss emailadress HIER opslaan in db, die dan weergeven als beheerder. 
//            //en dan in de login de call maken en daar de rest api aanspreken om een nieuwe gebruiker te maken
//            String input = "USERID/SHA256PW";
//
//            OutputStream os = conn.getOutputStream();
//            os.write(input.getBytes());
//            os.flush();
//
//            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//                throw new RuntimeException("Failed : HTTP error code : "
//                        + conn.getResponseCode());
//            }
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(
//                    (conn.getInputStream())));
//
//            String output;
//            System.out.println("Output from Server .... \n");
//            while ((output = br.readLine()) != null) {
//                System.out.println(output);
//            }
//
//            conn.disconnect();
//
//        } catch (MalformedURLException e) {
//
//            e.printStackTrace();
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//
//        }
//    }
}
