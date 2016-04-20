/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author Timva
 */
public class ProductData {
    
    
    private ProductBeheer pb;

   

    public ProductData(ProductBeheer pb) {
        this.pb = pb;
    }
    
    public void maakProducten()
    {
        //leergebieden, kleuters, Hogent, "foto1", "klok", "Dit is een klok", 1, 1, 1, "B1002"
        List<Leergebied> leergebieden = new ArrayList();
        List<Leergebied> leergebieden2 = new ArrayList();
        
        Leergebied tellen = new Leergebied("Tellen");
        tellen.setId(0);
        Leergebied knutselen = new Leergebied("Knutselen");
         knutselen.setId(1);
        leergebieden.add(tellen);
        leergebieden.add(knutselen);
        leergebieden2.add(tellen);
      // Image foto = new image()
        Doelgroep kleuters = new Doelgroep("Kleuters");
      //  Doelgroep kleuters = new Doelgroep("Kleuters");
       Firma Hogent = new Firma("Hogent", "personeel@hogent.be");
       Firma UGent = new Firma("UGent");
       Hogent.setId(1);
       
      
       //new Product(leergebieden, kleuters, UGent, naam, omschrijving,artikelnummer , prijs, aantal, plaats)
        pb.voegProductToe(new Product(leergebieden, kleuters, Hogent, "Klok", "Dit is een klok", 1, 1, 1, "B1002"));
        pb.voegProductToe(new Product(leergebieden, kleuters, Hogent, "Wereldkaart" , "Een kaart van 50 cm op 30 cm" , 2 , 2 , 2 , "B1038"));
        pb.voegProductToe(new Product(leergebieden, kleuters, UGent, "Dobbelstenen met schatkist" , "Doos met spelletjes" , 6 , 10 , 5 , "B1038"));
        pb.voegProductToe(new Product(leergebieden, kleuters, UGent, "Blanco draaischijf" , "met verschillende blanco schijven in hard papier" , 7 , 25 , 3 , "B1038"));
    
    }
     
    
    
    
}
