package domein;

import java.util.List;

public class DomeinController {

	private Gebruiker aangemeldeGebruiker;
	private ProductRepository productRepo;
	private Product product;

	/**
	 * 
	 * @param foto
	 * @param naam
	 * @param omschrijving
	 * @param artikelnummer
	 * @param prijs
	 * @param aantal
	 * @param plaats
	 * @param firma
	 * @param doelgroep
	 * @param leergebied
	 */
	public void voegProductToe(String foto, String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, String firma, String doelgroep, String leergebied) {
		// TODO - implement DomeinController.voegProductToe
		throw new UnsupportedOperationException();
	}

	

	/**
	 * 
	 * @param naam
	 * @param omschrijving
	 * @param artikelnummer
	 * @param prijs
	 * @param aantal
	 * @param plaats
	 * @param firma
	 * @param doelgroep
	 * @param leergebied
	 */
	public List<Product> geefOverzichtProducten(String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, String firma, String doelgroep, String leergebied) {
		// TODO - implement DomeinController.geefOverzichtProducten
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param artikelnummer
	 */
	public Product getProduct(int artikelnummer) {
		return this.product;
	}

	/**
	 * 
	 * @param product
	 */
	public void wijzigProduct(Product product) {
		// TODO - implement DomeinController.wijzigProduct
		throw new UnsupportedOperationException();
	}

}