package domein;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Doelgroep")
public class Doelgroep implements Serializable {

    private String naam;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    
    
    
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Doelgroep(String naam) {
        this.naam = naam;
    }

    public Doelgroep() {
        //a
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
