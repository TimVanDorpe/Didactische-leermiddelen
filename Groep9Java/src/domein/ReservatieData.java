/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.collections.ObservableList;


/**
 *
 * @author Jens
 */
public class ReservatieData {

    private ReservatieBeheer rb;
    private ProductBeheer pb;
    private ObservableList<Product> productenLijst;

    public ReservatieData(ReservatieBeheer rb, ProductBeheer pb) {
        this.rb = rb;
        this.pb = pb;
        productenLijst = pb.getProductenLijst();
    }
    
    public void maakReservaties(){
        
        Calendar startDatum1 = new GregorianCalendar(2016, 5, 2, 8, 0, 0);
        Calendar eindDatum1 = new GregorianCalendar(2016, 5, 6, 17, 0, 0);
        
        Calendar startDatum2 = new GregorianCalendar(2016, 5, 9, 8, 0, 0);
        Calendar eindDatum2 = new GregorianCalendar(2016, 5, 13, 17, 0,0);
        
        String gebruiker1 = "student1@hogent.be";
        String gebruiker2 = "student2@hogent.be";
        
        Product p1 = productenLijst.get(0);
        Product p2 = productenLijst.get(1);
        Product p3 = productenLijst.get(2);
        
        
        
        rb.addReservatie(new Reservatie(startDatum1, eindDatum1, gebruiker1, p3, 3));
        rb.addReservatie(new Reservatie(startDatum1, eindDatum1, gebruiker2, p3, 2));
        rb.addReservatie(new Reservatie(startDatum2, eindDatum2, gebruiker1, p2, 1));
        rb.addReservatie(new Reservatie(startDatum2, eindDatum2, gebruiker1, p1, 2));
        
        
        
        
        
    }
    
    
    
    

}
