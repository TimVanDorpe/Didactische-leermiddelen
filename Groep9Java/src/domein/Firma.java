package domein;

public class Firma {

	private String naam;
	private String emailContactPersoon;

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

        
        
        
        
}