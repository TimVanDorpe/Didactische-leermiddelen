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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Tim
 */
public class ProductenMapper {
    
      public List<Product> geefProducten() {
        //Simulatie databank
        Collection<Leergebied> leergebieden = null;
        Leergebied tellen = new Leergebied("tellen");
        leergebieden.add(tellen);
        Doelgroep kleuters = new Doelgroep("Kleuters");
        Firma Hogent = new Firma("Hogent");
        
        List<Product> product = new ArrayList<>();
       //Opvullen met producten
       product.add(new Product(leergebieden, kleuters, Hogent, "a", "Klok", "Dit is een klok", 1 , 1.08, 18 , "Gent"));              
       
        return product;
    }
    
}
