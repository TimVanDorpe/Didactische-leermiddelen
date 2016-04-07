package domein;

import java.util.*;

public class Product {

	private Collection<Leergebied> leergebied;
	private Doelgroep doelgroep;
	private Firma firma;
	private String foto;
	private String naam;
	private String omschrijving;
	private int artikelnummer;
	private double prijs;
	private int aantal;
	private String plaats;

    public Product(Collection<Leergebied> leergebied, Doelgroep doelgroep, Firma firma, String foto, String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats) {
        this.leergebied = leergebied;
        this.doelgroep = doelgroep;
        this.firma = firma;
        this.foto = foto;
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.artikelnummer = artikelnummer;
        this.prijs = prijs;
        this.aantal = aantal;
        this.plaats = plaats;
    }

    public Collection<Leergebied> getLeergebied() {
        return leergebied;
    }

    public void setLeergebied(Collection<Leergebied> leergebied) {
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
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
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
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }
        public void wijzig(Product product)
        {
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