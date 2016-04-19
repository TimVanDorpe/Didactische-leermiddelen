/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Calendar;

/**
 *
 * @author Jens
 */
public class Reservatie {
    
    private Calendar startDatum, eindDatum;
    private String gebruiker;
    private Product gereserveerdProduct;
    private int gereserveerdAantal;

    public Reservatie(Calendar startDatum, Calendar eindDatum, String gebruiker, Product gereserveerdProduct, int gereserveerdAantal) {
        setStartDatum(startDatum);
        setEindDatum(eindDatum);
        setGebruiker(gebruiker);
        setGereserveerdProduct(gereserveerdProduct);
        setGereserveerdAantal(gereserveerdAantal);
    }

    public Calendar getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(Calendar startDatum) {
        this.startDatum = startDatum;
    }

    public Calendar getEindDatum() {
        return eindDatum;
    }

    public void setEindDatum(Calendar eindDatum) {
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
    
    
    
    
    
    
}