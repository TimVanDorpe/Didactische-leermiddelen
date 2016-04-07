/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author Tim
 */
public class ProductenBeheer {
       
    
	private Product product;
        private List<Product> producten;

	
	public void voegProductToe(String foto, String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma, Doelgroep doelgroep, Collection<Leergebied> leergebied) {
		
		producten.add(new Product(leergebied, doelgroep, firma, foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats));
	}

	
	public List<Product> geefOverzichtProducten() {
		
		return producten;
	}

	public Product getProduct(int artikelnummer) {
		return producten.get(artikelnummer);
	}

	
	public void wijzigProduct(Product product) {
		
                 product.wijzig(product);      
       
            }
		
	}
    

