/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Timva
 */
public class ProductData {
    
    
    private ProductBeheer pb;

    
    
    
   

    public ProductData(ProductBeheer pb) {
        this.pb = pb;
    }
    
    public void maakProducten() throws MalformedURLException 
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
       
       URL legeFoto = new URL("http://i.imgur.com/tsvNPVH.png");
       //new Product(leergebieden, kleuters, UGent, naam, omschrijving,artikelnummer , prijs, aantal, plaats)
        pb.voegProductToe(new Product(leergebieden, doelgroepen, Hogent, legeFoto,"Klok", "Dit is een klok", 1, 1, 1, "B1002"));
        pb.voegProductToe(new Product(leergebieden, doelgroepen, Hogent, legeFoto,"Wereldkaart" , "Een kaart van 50 cm op 30 cm" , 2 , 2 , 2 , "B1038"));
        pb.voegProductToe(new Product(leergebieden, doelgroepen, UGent,legeFoto, "Dobbelstenen met schatkist" , "Doos met spelletjes" , 6 , 10 , 5 , "B1038"));
        pb.voegProductToe(new Product(leergebieden, doelgroepen, UGent,legeFoto, "Blanco draaischijf" , "met verschillende blanco schijven in hard papier" , 7 , 25 , 3 , "B1038"));
    
    }
     
    
    
    
}
