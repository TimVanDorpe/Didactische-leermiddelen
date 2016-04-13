package domein;

import java.sql.Blob;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.image.Image;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DomeinController extends Observable{

    public final String PERSISTENCE_UNIT_NAME = "Groep09";
    private EntityManager em;
    private EntityManagerFactory emf;
    
    
    private Gebruiker aangemeldeGebruiker;
    private Product product;
    private ProductBeheer pb;

    public DomeinController() {
        openPersistentie();
        pb = new ProductBeheer(em , emf);
        
    }
    
       private void openPersistentie() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
    }

    public void closePersistentie() {
        em.close();
        emf.close();
    }

    public void voegProductToe(Blob foto, String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma, Doelgroep doelgroep, List<Leergebied> leergebied) {

        pb.voegProductToe(new Product(leergebied, doelgroep, firma, foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats));
    }
    public void voegProductToeZonderFoto(String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma, Doelgroep doelgroep, List<Leergebied> leergebied) {

        pb.voegProductToe(new Product(leergebied, doelgroep, firma, naam, omschrijving, artikelnummer, prijs, aantal, plaats));
    }

    public List<Product> geefOverzichtProducten() {
        return pb.geefOverzichtProducten();
    }

    public Product getProduct(int artikelnummer) {
        return pb.getProduct(artikelnummer);
    }

    public void wijzigProduct(Blob foto, String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma, Doelgroep doelgroep, List<Leergebied> leergebied) {
        pb.wijzigProduct(new Product(leergebied, doelgroep, firma, foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats));
        notifyObservers();
    }
     public void wijzigProductZonderFoto(String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma, Doelgroep doelgroep, List<Leergebied> leergebied) {
        pb.wijzigProduct(new Product(leergebied, doelgroep, firma, naam, omschrijving, artikelnummer, prijs, aantal, plaats));
        notifyObservers();
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

    public ObservableList<Product> zoekOpTrefwoord(String trefwoord) {
        return pb.zoekOpTrefwoord(trefwoord);
    }

}
