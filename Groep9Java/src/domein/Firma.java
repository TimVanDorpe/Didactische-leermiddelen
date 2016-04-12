package domein;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Firma")
public class Firma {

	private String naam;
	private String emailContactPersoon;
    @Id
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
  
        
        
        
        
}