/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import util.Helper;

/**
 *
 * @author Jens
 */
@Entity(name = "Reservatie")
public class Reservatie implements Serializable{

    private LocalDate startDatum, eindDatum;
    private String gebruiker;
    private Product gereserveerdProduct;
    private int gereserveerdAantal;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Reservatie(GregorianCalendar startDatum, GregorianCalendar eindDatum, String gebruiker, Product gereserveerdProduct, int gereserveerdAantal) {
        setStartDatum(startDatum.toZonedDateTime().toLocalDate());
        setEindDatum(eindDatum.toZonedDateTime().toLocalDate());
        setGebruiker(gebruiker);
        setGereserveerdProduct(gereserveerdProduct);
        setGereserveerdAantal(gereserveerdAantal);
    }

    public Reservatie() {
    }

    public Reservatie(LocalDate startDatum, LocalDate eindDatum, String gebruiker, Product gereserveerdProduct, int gereserveerdAantal) {
        setStartDatum(startDatum);
        setEindDatum(eindDatum);
        setGebruiker(gebruiker);
        setGereserveerdProduct(gereserveerdProduct);
        setGereserveerdAantal(gereserveerdAantal);
       
    }

    public LocalDate getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(LocalDate startDatum) {
        this.startDatum = startDatum;
    }

    public LocalDate getEindDatum() {
        return eindDatum;
    }

    public void setEindDatum(LocalDate eindDatum) {
        this.eindDatum = eindDatum;
    }

    
    
    


    public String getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(String gebruiker) {
        this.gebruiker = gebruiker;
    }

    public Product getGereserveerdProduct() {
        return gereserveerdProduct;
    }

    public void setGereserveerdProduct(Product gereserveerdProduct) {
        this.gereserveerdProduct = gereserveerdProduct;
    }

    public int getGereserveerdAantal() {
        return gereserveerdAantal;
    }

    public void setGereserveerdAantal(int gereserveerdAantal) {
        this.gereserveerdAantal = gereserveerdAantal;
    }

    public SimpleStringProperty productProperty() {
        SimpleStringProperty productSimple = new SimpleStringProperty();
        productSimple.set(gereserveerdProduct.getNaam());
        return productSimple;
    }

    public SimpleStringProperty aantalProperty() {
        SimpleStringProperty aantalSimple = new SimpleStringProperty();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(gereserveerdAantal);
        String aantalBuild = sb.toString();
        aantalSimple.set(aantalBuild);
        return aantalSimple;
    }

    public SimpleStringProperty studentProperty() {
        SimpleStringProperty studentSimple = new SimpleStringProperty();
        studentSimple.set(gebruiker);
        return studentSimple;
    }
    
    public SimpleStringProperty startDatumProperty() {
        
        
        SimpleStringProperty startDatumSimple = new SimpleStringProperty();
        startDatumSimple.set(startDatum.format(DateTimeFormatter.ISO_LOCAL_DATE));
        return startDatumSimple;
    }
    
    public SimpleStringProperty eindDatumProperty() {
        SimpleStringProperty EindDatumSimple = new SimpleStringProperty();
        EindDatumSimple.set(eindDatum.format(DateTimeFormatter.ISO_LOCAL_DATE));
        return EindDatumSimple;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

}
