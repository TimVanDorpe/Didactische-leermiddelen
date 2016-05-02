/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;


/**
 *
 * @author Jens
 */
public class ReservatieData {

    private ReservatieBeheer rb;
    private List<Product> plijst;

    public ReservatieData(ReservatieBeheer rb) {
        this.rb = rb;
        plijst = new ArrayList<>();
    }
    
    public void maakReservaties(){
        
        
        maakProducten();
        
        GregorianCalendar startDatum1 = new GregorianCalendar(2016, 3, 6, 8, 0, 0);
        GregorianCalendar eindDatum1 = new GregorianCalendar(2016, 3, 10, 17, 0, 0);
        
        GregorianCalendar startDatum2 = new GregorianCalendar(2016, 3, 13, 8, 0, 0);
        GregorianCalendar eindDatum2 = new GregorianCalendar(2016, 3, 17, 17, 0,0);
        
        String gebruiker1 = "student1@hogent.be";
        String gebruiker2 = "student2@hogent.be";
        
        Product p1 = plijst.get(0);
        Product p2 = plijst.get(1);
        Product p3 = plijst.get(2);
        Product p4 = plijst.get(3);
        
        
        
        rb.addReservatie(new Reservatie(startDatum1.toZonedDateTime().toLocalDate() , eindDatum1.toZonedDateTime().toLocalDate(), gebruiker1, p4, 5, 2, 3));
        rb.addReservatie(new Reservatie(startDatum1.toZonedDateTime().toLocalDate(), eindDatum1.toZonedDateTime().toLocalDate(), gebruiker2, p3, 6 ,0 ,6));
        rb.addReservatie(new Reservatie(startDatum2.toZonedDateTime().toLocalDate(), eindDatum2.toZonedDateTime().toLocalDate(), gebruiker1, p2, 12 ,12 ,0));
        rb.addReservatie(new Reservatie(startDatum2.toZonedDateTime().toLocalDate(), eindDatum2.toZonedDateTime().toLocalDate(), gebruiker1, p1, 10 , 5 ,4));
        
        
        
        
        
    }
    
    
    //TIJDELIJJKK
    
     private void maakProducten()
    {
        //leergebieden, kleuters, Hogent, "foto1", "klok", "Dit is een klok", 1, 1, 1, "B1002"
        List<Leergebied> leergebieden = new ArrayList();
        List<Leergebied> leergebieden2 = new ArrayList();
                List<Doelgroep> doelgroepen = new ArrayList();

        Leergebied tellen = new Leergebied("");
        tellen.setId(0);
        Leergebied knutselen = new Leergebied("");
         knutselen.setId(1);
        leergebieden.add(tellen);
        leergebieden.add(knutselen);
        leergebieden2.add(tellen);
      // Image foto = new image()
        Doelgroep kleuters = new Doelgroep("");
       Doelgroep lager = new Doelgroep("");
       doelgroepen.add(kleuters);
       doelgroepen.add(lager);
       Firma Hogent = new Firma("Hogent", "personeel@hogent.be");
       Firma UGent = new Firma("UGent");
       Hogent.setId(1);
       
       URL legeFoto = null;
        try {
            legeFoto = new URL("http://i.imgur.com/tsvNPVH.png");
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReservatieData.class.getName()).log(Level.SEVERE, null, ex);
        }
       //new Product(leergebieden, kleuters, UGent, naam, omschrijving,artikelnummer , prijs, aantal, plaats)
        plijst.add(new Product(leergebieden, doelgroepen, Hogent, legeFoto,"Klok", "Dit is een klok", 1, 1, 1, "B1002"));
        plijst.add(new Product(leergebieden, doelgroepen, Hogent, legeFoto,"Wereldkaart" , "Een kaart van 50 cm op 30 cm" , 2 , 2 , 2 , "B1038"));
        plijst.add(new Product(leergebieden, doelgroepen, UGent,legeFoto, "Dobbelstenen met schatkist" , "Doos met spelletjes" , 6 , 10 , 5 , "B1038"));
        plijst.add(new Product(leergebieden, doelgroepen, UGent,legeFoto, "Blanco draaischijf" , "met verschillende blanco schijven in hard papier" , 7 , 25 , 3 , "B1038"));
    
    }
     
    
    
    

}
