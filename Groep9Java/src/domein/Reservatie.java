/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.util.Calendar;
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

    private GregorianCalendar startDatum, eindDatum;
    private String gebruiker;
    private Product gereserveerdProduct;
    private int gereserveerdAantal;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Reservatie(GregorianCalendar startDatum, GregorianCalendar eindDatum, String gebruiker, Product gereserveerdProduct, int gereserveerdAantal) {
        setStartDatum(startDatum);
        setEindDatum(eindDatum);
        setGebruiker(gebruiker);
        setGereserveerdProduct(gereserveerdProduct);
        setGereserveerdAantal(gereserveerdAantal);
    }

    public Reservatie() {
    }
    
    

    public GregorianCalendar getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(GregorianCalendar startDatum) {
        this.startDatum = startDatum;
    }

    public GregorianCalendar getEindDatum() {
        return eindDatum;
    }

    public void setEindDatum(GregorianCalendar eindDatum) {
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
        startDatumSimple.set(Helper.format(startDatum));
        return startDatumSimple;
    }
    
    public SimpleStringProperty eindDatumProperty() {
        SimpleStringProperty EindDatumSimple = new SimpleStringProperty();
        EindDatumSimple.set(Helper.format(eindDatum));
        return EindDatumSimple;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

}
