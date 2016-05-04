/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jens
 */
@Entity(name = "Reservatie")
public class Reservatie implements Serializable {

    private LocalDate startDatum, eindDatum;
    private String gebruiker;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    private Product gereserveerdProduct;
    private int gereserveerdAantal, opTeHalen, teruggebracht;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean nogWeergeven = true;
    private String status;

  
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
        berekenStatus();
    }

    public Reservatie(LocalDate startDatum, LocalDate eindDatum, String gebruiker, Product gereserveerdProduct, int gereserveerdAantal) {
        setStartDatum(startDatum);
        setEindDatum(eindDatum);
        setGebruiker(gebruiker);
        setGereserveerdProduct(gereserveerdProduct);
        setGereserveerdAantal(gereserveerdAantal);
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
        if (opTeHalen + teruggebracht > gereserveerdAantal) {
            throw new IllegalArgumentException("het aantal op te halen met het aantal teruggebracht kan niet groter zijn dan het totaal aantal");
        }
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
    
    public void berekenStatus(){
        String status = "";
        // bereken of de einddatum kleiner is dan vandaag
        if (eindDatum.isBefore(LocalDate.now())) {
        
        
        
            // bereken of het aantal optehalen + teruggebracht = totaal aantal
            // zo wel dan "voltooid" (voltooide reservaties zullen niet meer getoond worden)
            if (opTeHalen + teruggebracht == gereserveerdAantal) {
        
                setNogWeergeven(false);
                // zo niet dan moet met iets teruggeven zoals "te laat"
            } else if( teruggebracht < gereserveerdAantal - opTeHalen) {
                status ="Niet alles teruggebracht";
            }

             // als de startdatum  kleiner is dan vandaag en einddatum groter dan vandaag {
        } else if(startDatum.isBefore(LocalDate.now()) && eindDatum.isAfter(LocalDate.now())) {
            if (opTeHalen == gereserveerdAantal) {
            status ="Klaar om op te halen";
            }else{
                status= "Uitgeleend";
            }
            
        } else if(startDatum.isAfter(LocalDate.now())){
            if(LocalDate.now().getDayOfYear() - startDatum.getDayOfYear() <= 7 ){
                 status ="Klaar te leggen";
            }
        }
        
        
        setStatus(status);
    }
      public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
  
    public SimpleStringProperty getStatusProperty() {

        SimpleStringProperty status = new SimpleStringProperty();
        status.set(getStatus());
        return status;
        
    }
    
    public boolean isNogWeergeven() {
        return nogWeergeven;
    }
    
    public void setNogWeergeven(boolean nogWeergeven) {
        this.nogWeergeven = nogWeergeven;
    }

    

}
