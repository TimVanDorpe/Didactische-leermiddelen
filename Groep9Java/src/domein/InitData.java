/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Timva
 */
public class InitData {
    
    
    private ProductBeheer pb;
   

    public InitData(ProductBeheer pb) {
        this.pb = pb;
    }
    
    public void maakProducten()
    {
        //leergebieden, kleuters, Hogent, "foto1", "klok", "Dit is een klok", 1, 1, 1, "B1002"
        List<Leergebied> leergebieden = new ArrayList();
        Leergebied tellen = new Leergebied("tellen");
        leergebieden.add(tellen);
        Doelgroep kleuters = new Doelgroep("Kleuters");
       Firma Hogent = new Firma("Hogent");
       Hogent.setId(1);
       
        pb.voegProductToe(new Product(leergebieden, kleuters, Hogent, "foto1", "klok", "Dit is een klok", 1, 1, 1, "B1002"));
    
    }
     
    
    
}
