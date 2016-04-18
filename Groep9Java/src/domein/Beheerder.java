package domein;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Beheerder {
    private String email, wachtwoord, naam;
    private String telefoonnummer;

    public Beheerder(String email, String wachtwoord) {
        this.email = email;
        this.wachtwoord = wachtwoord;
    }

    public Beheerder(String email, String wachtwoord, String naam, String telefoonnummer) {
        setEmail(email);
        setNaam(naam);
        setTelefoonnummer(telefoonnummer);
        setWachtwoord(wachtwoord);
    }

    String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    Pattern pattern = Pattern.compile(regex);
    public void setEmail(String email) {
         Matcher matcher = pattern.matcher(email);
    if( matcher.matches()){
        this.email = email;
    }
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
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
    
    
    
    
}