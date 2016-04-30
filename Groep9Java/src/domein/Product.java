package domein;

import java.io.Serializable;
import java.net.URL;
import java.sql.Blob;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "Product")
public class Product implements Serializable {
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Leergebied> leergebieden;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Doelgroep> doelgroepen;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Firma firma;
    private URL foto;
    //@Column(unique=true)
    private String naam ;
    private String omschrijving;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    
    private int artikelnummer;
    private double prijs;
    private int aantal ;
    private String plaats ;

    public Product(List<Leergebied> leergebied, List<Doelgroep> doelgroep, Firma firma, URL foto, String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats) {
        setLeergebieden(leergebied);
        setDoelgroepen(doelgroep);
        setFirma(firma);
        setFoto(foto);
        setNaam(naam);
        setOmschrijving(omschrijving);
        setArtikelnummer(artikelnummer);
        setPrijs(prijs);
        setAantal(aantal);
        setPlaats(plaats);
    }
    public Product(String naam, int aantal)
    {
        setNaam(naam);
        setAantal(aantal);
    }
    
    
     public Product(List<Leergebied> leergebied, List<Doelgroep> doelgroep, Firma firma,String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats) {
        setLeergebieden(leergebied);
        setDoelgroepen(doelgroep);
        setFirma(firma);
        setNaam(naam);
        setOmschrijving(omschrijving);
        setArtikelnummer(artikelnummer);
        setPrijs(prijs);
        setAantal(aantal);
        setPlaats(plaats);
    }
    
    
    
    public Product(){
        
    }
    public List<Leergebied> getLeergebieden() {
        return leergebieden;
    }

    public void setLeergebieden(List<Leergebied> leergebieden) {
        this.leergebieden = leergebieden;
    }

    public List<Doelgroep> getDoelgroepen() {
        return doelgroepen;
    }

    public void setDoelgroepen(List<Doelgroep> doelgroepen) {
        this.doelgroepen = doelgroepen;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public URL getFoto() {
        return foto;
    }

    public void setFoto(URL foto) {
        this.foto = foto;
    }

    public String getNaam() {
        return naam;
    }
    
    

    /**
     *
     * @param naam
     */
    public void setNaam(String naam) {

        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(naam);
        boolean b = m.find();

        if (naam.isEmpty()) {
            throw new IllegalArgumentException("Naam mag niet leeg zijn");
        }
        if (b) {
            throw new IllegalArgumentException("Naam mag geen speciale tekens bevatten");
        }
        this.naam = naam;
    }

    public SimpleStringProperty naamProperty() {
         SimpleStringProperty naamSimple = new SimpleStringProperty();
        naamSimple.set(naam);
        return naamSimple;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public SimpleStringProperty omschrijvingProperty() {
     SimpleStringProperty omschrijvingSimple = new SimpleStringProperty();
     omschrijvingSimple.set(omschrijving);
        return omschrijvingSimple;
    }

    public int getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(int artikelnummer) {
        if (artikelnummer < 0) {
            throw new IllegalArgumentException("Artikelnummer moet positief zijn");
        }
        this.artikelnummer = artikelnummer;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        if(prijs<0.0)
            throw new IllegalArgumentException("Prijs moet positief zijn");
        this.prijs = prijs;
    }

    public int getAantal() {
        return aantal;   
    }

    public void setAantal(int aantal) {
        if (aantal <= 0) {
            throw new IllegalArgumentException("Aantal moet positief zijn");
        }
        this.aantal = aantal;
    }

    public SimpleStringProperty aantalProperty() {
        SimpleStringProperty aantalSimple = new SimpleStringProperty();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(aantal);
        String aantalBuild = sb.toString();
        aantalSimple.set(aantalBuild);
        return aantalSimple;
    }

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public SimpleStringProperty plaatsProperty() {
       SimpleStringProperty plaatsSimple = new SimpleStringProperty();
     plaatsSimple.set(plaats);
        return plaatsSimple;
    }
   
}
