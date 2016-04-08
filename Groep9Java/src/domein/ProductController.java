package domein;

import java.util.Collection;
import java.util.List;

public class ProductController {

	private Gebruiker aangemeldeGebruiker;
	private Product product;
        private ProductenBeheer pb;
	
	public void voegProductToe(String foto, String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma, Doelgroep doelgroep, List<Leergebied> leergebied) {
            
		pb.voegProductToe(new Product(leergebied, doelgroep, firma, foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats));
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