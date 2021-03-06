package gui;

import domein.Beheerder;
import domein.BeheerderController;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
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
    private Button btnAnnuleren;
    @FXML
    private Button btnBeheerderVerwijderen;
    @FXML
    private Label lblError, lblNaam, lblEmail, lblWachtwoord, lblDetailsBeheerder;

    private BeheerderController bc;
    @FXML
    private Button btnToevoegen;

    @FXML
    private Region region;

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
        hoofdAdmin();
        btnToevoegen.setVisible(false);
        btnAnnuleren.setVisible(false);
        if (bc.getSelectionModelEmpty()) {

            btnBeheerderVerwijderen.setVisible(false);
            txtEmail.setDisable(true);
            txtNaam.setDisable(true);
            txtWachtwoord.setDisable(true);
            btnAnnuleren.setVisible(false);

        }

    }

    public void hoofdAdmin() {

        if (bc.getAangemeldeBeheerder().getEmail().equalsIgnoreCase("hoofdbeheerder")) {//isHoofdbeheerder is altijd false???a
            btnBeheerderVerwijderen.setVisible(true);
            txtEmail.setVisible(true);
            txtNaam.setVisible(true);
            txtWachtwoord.setVisible(true);
            lblWachtwoord.setVisible(true);
            lblNaam.setVisible(true);
            lblEmail.setVisible(true);
            btnNieuweBeheerder.setVisible(true);
            region.setVisible(true);

            lblDetailsBeheerder.setVisible(true);
        } else {

            btnBeheerderVerwijderen.setVisible(false);
            txtEmail.setVisible(false);
            txtNaam.setVisible(false);
            txtWachtwoord.setVisible(false);
            btnAnnuleren.setVisible(false);
            lblWachtwoord.setVisible(false);
            lblNaam.setVisible(false);
            lblEmail.setVisible(false);
            btnNieuweBeheerder.setVisible(false);
            btnToevoegen.setVisible(false);
            btnAnnuleren.setVisible(false);
            lblDetailsBeheerder.setVisible(false);
            region.setVisible(true);

        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {

            lblError.setText("");

            Beheerder beh = (Beheerder) arg;
            txtNaam.setText(beh.getNaam());
            txtEmail.setText(beh.getEmail());
            txtWachtwoord.setText(beh.getWachtwoord());

            //alles terug enablen als er iets geselecteerd wordt
            btnBeheerderVerwijderen.setVisible(true);
            txtEmail.setDisable(true);
            txtNaam.setDisable(true);
            txtWachtwoord.setDisable(true);
            btnAnnuleren.setVisible(false);
            btnToevoegen.setVisible(false);
            hoofdAdmin();
            maakLabelsTerugNormaal();

        }
    }

    @FXML
    private void nieuweBeheerderToevoegen(ActionEvent event) {
        resetWaarden();
        btnToevoegen.setVisible(true);
        btnAnnuleren.setVisible(true);

        Beheerder beh = new Beheerder();

        txtNaam.setPromptText("Naam van de nieuwe beheerder");
        txtEmail.setDisable(false);
        txtNaam.setDisable(false);
        txtWachtwoord.setDisable(false);

    }

    @FXML
    private void toevoegenBeheerder(ActionEvent event) {

        String naam = txtNaam.getText();
        String email = txtEmail.getText();
        String wachtwoord = txtWachtwoord.getText();

        if (isInputValid()) {
            bc.voegBeheerderToe(naam, email, wachtwoord);
            btnToevoegen.setVisible(false);
            btnAnnuleren.setVisible(false);
            btnBeheerderVerwijderen.setVisible(true);
            txtEmail.setDisable(true);
            txtNaam.setDisable(true);
            txtWachtwoord.setDisable(true);
            maakLabelsTerugNormaal();
        }

    }

    private void maakLabelsTerugNormaal() {

        lblNaam.setText("Naam");
        lblNaam.setTextFill(Color.web("#000000"));
        lblEmail.setText("Email");
        lblEmail.setTextFill(Color.web("#000000"));
        lblWachtwoord.setText("Wachtwoord");
        lblWachtwoord.setTextFill(Color.web("#000000"));

    }

    private boolean isInputValid() {
        maakLabelsTerugNormaal();     
        
        boolean validInput = true;
        String naam = txtNaam.getText();
        String message = "";
        String regexEmail = "^(.+)@(.+)$"; 
        String regexNaam = "^[\\p{L} .'-]+$";
        Pattern patternEmail = Pattern.compile(regexEmail);
        Matcher matcherEmail = patternEmail.matcher(txtEmail.getText());
        Pattern patternNaam = Pattern.compile(regexNaam);
        Matcher matcherNaam = patternNaam.matcher(txtNaam.getText());
        
        
        if(!matcherNaam.matches())
        {
        message += "Naam heeft geen juist formaat\n";
         lblNaam.setText("Naam*");
         lblNaam.setTextFill(Color.web("#F20000"));
         validInput = false;       
        }       
        
        
        if(!matcherEmail.matches())
        {
        message += "Email heeft geen juist formaat\n";
         lblEmail.setText("Email*");
         lblEmail.setTextFill(Color.web("#F20000"));
         validInput = false;
        }
        
        if (txtNaam.getText().equals("")) {

            message += "Naam is verplicht\n";
            lblNaam.setText("Naam*");
            lblNaam.setTextFill(Color.web("#F20000"));
            validInput = false;
        }
        String email = txtEmail.getText();

        if (txtEmail.getText().equals("")) {
            lblEmail.setText("Email*");
            lblEmail.setTextFill(Color.web("#F20000"));
            message += "Email is verplicht\n";
            validInput = false;
        }

        if (!bc.isEmailUniek(txtEmail.getText())) {
            lblEmail.setText("Email*");
            lblEmail.setTextFill(Color.web("#F20000"));

            message += "Email moet uniek zijn\n";
            validInput = false;

        }
        String wachtwoord = txtWachtwoord.getText();
        if (txtWachtwoord.getText().equals("")) {
            lblWachtwoord.setText("Wachtwoord*");
            lblWachtwoord.setTextFill(Color.web("#F20000"));
            message += "Wachtwoord is verplicht\n";
            validInput = false;
        }
        lblError.setText(message);
        lblError.setTextFill(Color.web("#F20000"));

        return validInput;
    }

    @FXML
    private void toevoegenAnnuleren(ActionEvent event) {
        maakLabelsTerugNormaal();

        btnToevoegen.setVisible(false);
        btnAnnuleren.setVisible(false);
        btnBeheerderVerwijderen.setVisible(true);

        resetWaarden();
    }

    @FXML
    private void resetWaarden(ActionEvent event) {
        maakLabelsTerugNormaal();
        resetWaarden();
    }

    private void resetWaarden() {
        txtNaam.setText("");
        txtEmail.setText("");
        txtWachtwoord.setText("");
        txtNaam.setPromptText("");
        bc.setGeselecteerdeBeheerder(null);
        btnBeheerderVerwijderen.setVisible(false);

    }

    @FXML
    private void verwijderBeheerder(ActionEvent event) {
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
