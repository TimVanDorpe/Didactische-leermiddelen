package domein;

import java.util.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Product")
public class Product {

    private List<Leergebied> leergebied;
    private Doelgroep doelgroep;
    private Firma firma;
    private String foto;
    private SimpleStringProperty naam = new SimpleStringProperty();
    private SimpleStringProperty omschrijving = new SimpleStringProperty();
    @Id
    private int artikelnummer;
    private double prijs;
    private SimpleStringProperty aantal = new SimpleStringProperty();
    private SimpleStringProperty plaats = new SimpleStringProperty();

    public Product(List<Leergebied> leergebied, Doelgroep doelgroep, Firma firma
            , String foto, String naam, String omschrijving, int artikelnummer
            , double prijs, int aantal, String plaats) {
        this.leergebied = leergebied;
        this.doelgroep = doelgroep;
        setFirma(firma);
        this.foto = foto;
        setNaam(naam);
        setOmschrijving(omschrijving);
        this.artikelnummer = artikelnummer;
        this.prijs = prijs;
        setAantal(aantal);
        setPlaats(plaats);
    }

    public List<Leergebied> getLeergebied() {
        return leergebied;
    }

    public void setLeergebied(List<Leergebied> leergebied) {
        this.leergebied = leergebied;
    }

    public Doelgroep getDoelgroep() {
        return doelgroep;
    }

    public void setDoelgroep(Doelgroep doelgroep) {
        this.doelgroep = doelgroep;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNaam() {
        return naam.get();
    }

    /**
     *
     * @param naam
     */
    public void setNaam(String naam) {
        this.naam.set(naam);
    }

    public SimpleStringProperty naamProperty() {
        return naam;
    }

    public String getOmschrijving() {
        return omschrijving.get();
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving.set(omschrijving);
    }

    public SimpleStringProperty omschrijvingProperty() {
        return omschrijving;
    }

    public int getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(int artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public int getAantal() {
        return Integer.parseInt(aantal.get());
    }

    public void setAantal(int aantal) {
        this.aantal.set(Integer.toString(aantal));
    }

    public SimpleStringProperty aantalProperty() {
        return aantal;
    }

    public String getPlaats() {
        return plaats.get();
    }

    public void setPlaats(String plaats) {
        this.plaats.set(plaats);
    }

    public SimpleStringProperty plaatsProperty() {
        return plaats;
    }

    public void wijzig(Product product) {
        setNaam(product.getNaam());
        setAantal(product.getAantal());
        setArtikelnummer(product.getArtikelnummer());
        setDoelgroep(product.getDoelgroep());
        setFirma(product.getFirma());
        setFoto(product.getFoto());
        setLeergebied(product.getLeergebied());
        setOmschrijving(product.getOmschrijving());
        setPlaats(product.getPlaats());
        setPrijs(product.getPrijs());

    }

}
