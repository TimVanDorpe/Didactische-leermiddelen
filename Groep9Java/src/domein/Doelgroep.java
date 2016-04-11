package domein;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Doelgroep")
public class Doelgroep {

	private String naam;
    @Id
    private Long id;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Doelgroep(String naam) {
        this.naam = naam;
    }
        
        public Doelgroep()
        {
        //a
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
        

}