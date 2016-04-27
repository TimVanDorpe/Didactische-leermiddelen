/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Beheerder;
import domein.BeheerderController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import util.Helper;

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
        }
    }

}
