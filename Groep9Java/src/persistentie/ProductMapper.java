/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Doelgroep;
import domein.Firma;
import domein.Leergebied;
import domein.Product;
import domein.ProductBeheer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tim
 */
public class ProductMapper implements IProductMapper {
    
        private ProductBeheer pb;
      public List<Product> geefProducten() {
          List<Product> producten = new ArrayList<>();
        //Simulatie databank
        List<Leergebied> leergebieden = new ArrayList();
        Leergebied tellen = new Leergebied("tellen");
        leergebieden.add(tellen);
        Doelgroep kleuters = new Doelgroep("Kleuters");
        Firma Hogent = new Firma("Hogent");
        
      
       
       //Opvullen met producten
      producten.add(new Product(leergebieden, kleuters, Hogent, "foto1", "klok", "Dit is een klok", 1, 1, 1, "B1002"));   
     // producten.add(new Product(leergebieden, kleuters, Hogent, foto, naam, omschrijving, 0, 0, 0, plaats));   
      
       
        return producten;
    }
    
}
