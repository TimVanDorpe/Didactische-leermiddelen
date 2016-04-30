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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import static org.eclipse.persistence.expressions.ExpressionOperator.Today;
import util.Helper;

/**
 *
 * @author Jens
 */
@Entity(name = "Reservatie")
public class Reservatie implements Serializable {

    private LocalDate startDatum, eindDatum;
    private String gebruiker;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Product gereserveerdProduct;
    private int gereserveerdAantal, opTeHalen, teruggebracht;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

  
    public Reservatie() {
    }

    public Reservatie(LocalDate startDatum, LocalDate eindDatum, String gebruiker, Product gereserveerdProduct, int gereserveerdAantal, int opTeHalen, int teruggebracht) {
        setStartDatum(startDatum);
        setEindDatum(eindDatum);
        setGebruiker(gebruiker);
        setGereserveerdProduct(gereserveerdProduct);
        setGereserveerdAantal(gereserveerdAantal);
        setOpTeHalen(opTeHalen);
        setTeruggebracht(teruggebracht);
    }
    
    

    public int getOpTeHalen() {
        return opTeHalen;
    }

    public void setOpTeHalen(int opTeHalen) {
        this.opTeHalen = opTeHalen;
    }

    public int getTeruggebracht() {
        return teruggebracht;
    }

    public void setTeruggebracht(int teruggebracht) {
        this.teruggebracht = teruggebracht;
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

    public SimpleStringProperty teruggebrachtProperty() {
        SimpleStringProperty aantalSimple = new SimpleStringProperty();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(teruggebracht);
        String aantalBuild = sb.toString();
        aantalSimple.set(aantalBuild);
        return aantalSimple;
    }

    public SimpleStringProperty opTeHalenProperty() {
        SimpleStringProperty aantalSimple = new SimpleStringProperty();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(opTeHalen);
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
    
    public SimpleStringProperty berekenStatus(){
        // bereken of de einddatum kleiner is dan vandaag
        // bereken of het aantal optehalen + teruggebracht = totaal aantal
        // zo niet dan moet met iets teruggeven zoals "te laat"
        // zo wel dan "voltooid" (voltooide reservaties zullen niet meer getoond worden)
        // als de startdatum  kleiner is dan vandaag en einddatum groter dan vandaag {
        // als op te halen = totaal aantal dan return "uit te lenen"
        // zo niet dan "uitgeleend"
        // als startdatum groter is en einddatum groter is dan niks weergeven?
        SimpleStringProperty status = new SimpleStringProperty();
        status.set("");
        boolean isEindDatumKleinerDanVandaag;
        boolean isAantalOk;
        if (eindDatum.isBefore(LocalDate.now())) {
            isEindDatumKleinerDanVandaag = true;
            status.set("te laat");
        } else {
            isEindDatumKleinerDanVandaag = false;
            status.set("er is nog tijd");
        }
        
        if(opTeHalen + teruggebracht == getTotaalAantal())
        {isAantalOk = true;
        status.set("aantal is ok");}
        else
        {isAantalOk = false;}
        
        if(isAantalOk && isEindDatumKleinerDanVandaag)
        {status.set("voltooid");}
//        else
//        {status.set("te laat");}
        
        
        if (startDatum.isBefore(LocalDate.now()) && eindDatum.isAfter(LocalDate.now())) {
            if (opTeHalen == getTotaalAantal()) {
                status.set("uit te lenen");
            } else {

            }
        } 
//        else {
//            status.set("Startdatum is achter vandaag of einddation is voor vandaag");
//        }
        
        
        return status;
        
    }
    
    int getTotaalAantal()
    {
    return gereserveerdAantal + teruggebracht;
    
    }

}
