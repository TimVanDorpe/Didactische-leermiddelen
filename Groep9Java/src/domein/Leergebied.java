package domein;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Leergebied")
public class Leergebied {

	private String naam;
    
        @Id
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

        
        
        
}