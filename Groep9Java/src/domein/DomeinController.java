package domein;

import java.util.Collection;
import java.util.List;

public class DomeinController {

	private Gebruiker aangemeldeGebruiker;
	private Product product;
        private ProductenBeheer pb;
	
	public void voegProductToe(String foto, String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma, Doelgroep doelgroep, Collection<Leergebied> leergebied) {
		pb.voegProductToe(foto, naam, omschrijving, artikelnummer, prijs, aantal , plaats , firma, doelgroep, leergebied);
	}	
	public List<Product> geefOverzichtProducten() {
		return pb.geefOverzichtProducten();
	}
        public Product getProduct(int artikelnummer) {
		return pb.getProduct(artikelnummer);
	}	
	public void wijzigProduct(Product product) {
		pb.wijzigProduct(product);
	}

	

}