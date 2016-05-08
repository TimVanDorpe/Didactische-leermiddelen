package domein;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Firma")
public class Firma implements Serializable {

    private String naam;
    private String emailContactPersoon;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Firma() {
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getEmailContactPersoon() {
        return emailContactPersoon;
    }

    public void setEmailContactPersoon(String emailContactPersoon) {
        this.emailContactPersoon = emailContactPersoon;
    }

    public Firma(String naam, String emailContactPersoon) {
        this.naam = naam;
        this.emailContactPersoon = emailContactPersoon;
    }

    public Firma(String naam) {
        this.naam = naam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
