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

public class ProductController extends Observable {

 

    private Gebruiker aangemeldeGebruiker;
    private Product huidigProduct;
    private ProductBeheer pb;
    private boolean selectionModelEmpty;

    public ProductController() {
        
        pb = new ProductBeheer();

    }    

    public void voegProductToe(String foto, String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma, Doelgroep doelgroep, ObservableList<Leergebied> leergebied) {

        pb.voegProductToe(new Product(leergebied, doelgroep, firma, foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats));
    }

    public void voegProductToeZonderFoto(String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma, Doelgroep doelgroep, ObservableList<Leergebied> leergebied) {

        pb.voegProductToe(new Product(leergebied, doelgroep, firma, naam, omschrijving, artikelnummer, prijs, aantal, plaats));
    }

    public Product getHuidigProduct() {
        return huidigProduct;
    }

   

    public Product getProduct(int artikelnummer) {
        return pb.getProduct(artikelnummer);
    }

    public void wijzigProduct(String foto, String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma, Doelgroep doelgroep, ObservableList<Leergebied> leergebied) {
        pb.wijzigProduct(new Product(leergebied, doelgroep, firma, foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats), huidigProduct);
         setChanged();
        notifyObservers();
    }

    public void wijzigProductZonderFoto(String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma, Doelgroep doelgroep, ObservableList<Leergebied> leergebied) {
        Product nieuwProduct = new Product(leergebied, doelgroep, firma, naam, omschrijving, artikelnummer, prijs, aantal, plaats);
        pb.wijzigProduct(nieuwProduct, huidigProduct);
         setChanged();
        notifyObservers();
    }

    public void verwijderProduct() {
        pb.verwijderProduct(huidigProduct);
    }

    public SortedList<Product> getProductSortedList() {
        //Wrap the FilteredList in a SortedList
        return pb.getSortedList(); //SortedList is unmodifiable
    }

    public void setGeselecteerdProduct(Product product) {
        this.huidigProduct = product;
        setChanged();
        notifyObservers(product);
    }

    //LEERGEBIED
    public ObservableList<Leergebied> getLeergebieden() {
        return pb.getLeergebieden();
    }

    public ObservableList<Leergebied> getToegevoegd() {
        return pb.getToegevoegd();
    }

//    public boolean geenToegevoegd() {
//        return pb.geenToegevoegd();
//    }
//    
//    public boolean geenLeergebieden() {
//        return pb.geenLeergebieden();
//    }
    public void voegLeergebiedToe(Leergebied naam) {
        pb.voegLeergebiedToe(naam);
    }

    public void verwijderLeergebied(Leergebied selectedItem) {
        pb.verwijderLeergebied(selectedItem);
    }

    //String methoden
    public ObservableList<String> getStringLeergebieden() {
        return pb.getStringLeergebieden();
    }

    public ObservableList<String> getStringLeergebiedenToegevoegd() {
        return pb.getStringLeergebiedenToegevoegd();
    }

    public void voegLeergebiedToeString(String naam) {
        pb.voegLeergebiedToeString(naam);
    }

    public void verwijderLeergebiedString(String naam) {
        pb.verwijderLeergebiedString(naam);
    }

    public Leergebied getLeergebiedFromString(String naam) {
        return pb.getLeergebiedFromString(naam);
    }

    public Leergebied getLeergebiedToegevoegdFromString(String naam) {
        return pb.getLeergebiedToegevoegdFromString(naam);
    }
    //EINDE LEERGEBIED

    public ObservableList<Product> zoekOpTrefwoord(String trefwoord) {
        return pb.zoekOpTrefwoord(trefwoord);
    }
    
    public void filterProductLijst( String trefwoord, int artikelnummer, double vanPrijs, double totPrijs,  String plaats, String firma, String email,String doelgroep, String leergebied ){
         pb.filterProductLijst( trefwoord,  artikelnummer,vanPrijs ,totPrijs ,   plaats,  firma, email,  doelgroep,  leergebied );
        setChanged();
         notifyObservers();
    }

   

    public void geefAlleProductenWeer() {
      pb.geefAlleProducten();
       
    }

   

    public void setSelectionModelEmpty(boolean b) {
        selectionModelEmpty = b;
    }

    public boolean getSelectionModelEmpty() {
        return selectionModelEmpty;
    }

    public boolean isNaamUniek(String naam) {
        return pb.isNaamUniek(naam);
    }

    public void updateDetailvenster() {
        setChanged();
        notifyObservers(huidigProduct);
    }
    
    
   
}
