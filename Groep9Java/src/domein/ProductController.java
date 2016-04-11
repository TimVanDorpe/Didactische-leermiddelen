package domein;

import java.util.Collection;
import java.util.List;
import java.util.Observable;
import javafx.collections.transformation.SortedList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProductController extends Observable{
    
    
     public final String PERSISTENCE_UNIT_NAME = "Groep09";
    private EntityManager em;
    private EntityManagerFactory emf;

    private Gebruiker aangemeldeGebruiker;
    private Product product;
    private ProductenBeheer pb;

    public ProductController() {
//        openPersistentie();
        pb = new ProductenBeheer(); 
        
    }
        
      private void openPersistentie() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
    }
       public void closePersistentie() {
        em.close();
        emf.close();
    }

    public void voegProductToe(String foto, String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma, Doelgroep doelgroep, List<Leergebied> leergebied) {

        pb.voegProductToe(new Product(leergebied, doelgroep, firma, foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats));
    }

    public List<Product> geefOverzichtProducten() {
        return pb.geefOverzichtProducten();
    }

    public Product getProduct(int artikelnummer) {
        return pb.getProduct(artikelnummer);
    }

    public void wijzigProduct(String foto, String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma, Doelgroep doelgroep, List<Leergebied> leergebied) {
        pb.wijzigProduct(new Product(leergebied, doelgroep, firma, foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats));
    }

    public SortedList<Product> getProductSortedList() {
        //Wrap the FilteredList in a SortedList
        return pb.getProductSortedList(); //SortedList is unmodifiable
    }
    public void setGeselecteerdProduct(Product product) {
        this.product = product;
        setChanged();
        notifyObservers(product);
    }

}
