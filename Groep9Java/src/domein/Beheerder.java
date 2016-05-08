package domein;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Beheerder")
public class Beheerder implements Serializable {
    @Column(unique=true)
    private String email;
    private String telefoonnummer, wachtwoord, naam;
    private boolean hoofdAdmin;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Beheerder(String email, String wachtwoord) {
        this.email = email;
        this.wachtwoord = wachtwoord;
    }

    public Beheerder(String email, String wachtwoord, String naam, Boolean hoofdAdmin , String telefoonnummer) {
        setEmail(email);
        setNaam(naam);
        setTelefoonnummer(telefoonnummer);
        setWachtwoord(wachtwoord);
        setHoofdAdmin(hoofdAdmin);
    }

    public Beheerder() {
    }

    public Beheerder(String naam, String email, String wachtwoord , Boolean hoofdAdmin) {
        setEmail(email);
        setNaam(naam);
        setWachtwoord(wachtwoord);
        setHoofdAdmin(hoofdAdmin);
    }

//    String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
//    Pattern pattern = Pattern.compile(regex);
    public void setEmail(String email) {
//         Matcher matcher = pattern.matcher(email);
//    if( matcher.matches()){
        this.email = email;
        // }
    }

    
  
    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public boolean isHoofdAdmin() {
        return hoofdAdmin;
    }

    public void setHoofdAdmin(boolean hoofdAdmin) {
        this.hoofdAdmin = hoofdAdmin;
    }
    
    
    
    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public String getEmail() {
        return email;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public String getNaam() {
        return naam;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SimpleStringProperty naamProperty() {
        SimpleStringProperty beheerderSimple = new SimpleStringProperty();
        beheerderSimple.set(getNaam());
        return beheerderSimple;
    }

    public SimpleStringProperty emailProperty() {
        SimpleStringProperty beheerderSimple = new SimpleStringProperty();
        beheerderSimple.set(getEmail());
        return beheerderSimple;
    }

}
