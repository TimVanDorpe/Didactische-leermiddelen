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
        List<Leergebied> leergebieden = new ArrayList();
        List<Leergebied> leergebieden2 = new ArrayList();
        List<Leergebied> leergebieden3 = new ArrayList();
              
                List<Doelgroep> doelgroepen = new ArrayList();

        Leergebied tellen = new Leergebied("Wetenschap");
        tellen.setId(0);
        Leergebied knutselen = new Leergebied("Biologie");
         knutselen.setId(1);
        leergebieden.add(tellen);
        leergebieden.add(knutselen);
        leergebieden2.add(tellen);
        
        leergebieden3.add(new Leergebied("Mens"));
        leergebieden3.add(new Leergebied("Maatschappij"));
        leergebieden3.add(new Leergebied("Geschiedenis"));
       Doelgroep kleuters = new Doelgroep("Kleuters");
       Doelgroep lager = new Doelgroep("");
       doelgroepen.add(kleuters);
       doelgroepen.add(lager);
       Firma Hogent = new Firma("Hogent", "personeel@hogent.be");
       Firma UGent = new Firma("UGent");
       Hogent.setId(1);
       
       URL legeFoto = new URL("http://i.imgur.com/tsvNPVH.png");
//       pb.deleteAlleProducten();
        pb.voegProductToe(new Product(leergebieden, doelgroepen, Hogent, legeFoto,"Klok", "Dit is een klok", 1, 1, 30, "B1002", 0));
        pb.voegProductToe(new Product(leergebieden3, doelgroepen, Hogent, legeFoto,"Wereldkaart" , "Een kaart van 50 cm op 30 cm" , 2 , 2 , 23 , "B1038", 0));
        pb.voegProductToe(new Product(leergebieden, doelgroepen, UGent,legeFoto, "Dobbelstenen met schatkist" , "Doos met spelletjes" , 6 , 10 , 20 , "B1038", 0));
        pb.voegProductToe(new Product(leergebieden, doelgroepen, UGent,legeFoto, "Blanco draaischijf" , "met verschillende blanco schijven in hard papier" , 7 , 25 , 25 , "B1038", 0));
    
    }
     
    
    
    
}
