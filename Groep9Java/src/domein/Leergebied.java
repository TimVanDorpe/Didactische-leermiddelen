package domein;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Leergebied")
public class Leergebied implements Serializable {

	private String naam;
    
        @Id
    private int id;

    public Leergebied() {
    }

        
        
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Leergebied(String naam) {
        this.naam = naam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

        
        
        
}