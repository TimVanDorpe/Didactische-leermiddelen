package domein;

import java.net.URL;
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

    private Product huidigProduct;
    private ProductBeheer pb;
    private boolean selectionModelEmpty;

    public ProductController() {

        pb = new ProductBeheer();

    }

    public List<Product> getProductenLijst(){
        return pb.getProductenLijst();
    }

    public ProductBeheer getPb() {
        return pb;
    }
    
    
    
    public void voegProductToe(URL foto, String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma, Doelgroep doelgroep, ObservableList<Leergebied> leergebied) {
        //isNaamUniek(naam);
        Product nieuwProduct = new Product(leergebied, doelgroep, firma, foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats);
        pb.voegProductToe(nieuwProduct);
    }

    public void voegProductToeZonderFoto(String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma, Doelgroep doelgroep, List<Leergebied> leergebied) {
        //isNaamUniek(naam);
        pb.voegProductToe(new Product(leergebied, doelgroep, firma, naam, omschrijving, artikelnummer, prijs, aantal, plaats));
    }

    public Product getHuidigProduct() {
        return huidigProduct;
    }

    public Product getProduct(int artikelnummer) {
        return pb.getProduct(artikelnummer);
    }

    public void wijzigProduct(URL foto, String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma, Doelgroep doelgroep, ObservableList<Leergebied> leergebied) {
        if (!naam.toLowerCase().equals(huidigProduct.getNaam().toLowerCase())) {
            //isNaamUniek(naam);
        }
        pb.wijzigProduct(new Product(leergebied, doelgroep, firma, foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats), huidigProduct);
        setChanged();
        notifyObservers();
    }

    public void wijzigProductZonderFoto(String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma, Doelgroep doelgroep, List<Leergebied> leergebied) {
        if (!naam.toLowerCase().equals(huidigProduct.getNaam().toLowerCase())) {
            //isNaamUniek(naam);
        }
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

    public List<Leergebied> getListLeergebiedToegevoegd() {
        return pb.getListLeergebiedToegevoegd();
    }
        //    public void wijzigLeergebiedenHuidigProduct(){
        //        huidigProduct.setLeergebied(leergebied);
        //    }

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

    public void voegToeAanLeergebieden(Leergebied leergebied) {
        pb.voegToeAanLeergebieden(leergebied);
    }

    //EINDE LEERGEBIED
    public ObservableList<Product> zoekOpTrefwoord(String trefwoord) {
        return pb.zoekOpTrefwoord(trefwoord);
    }

    public void filterProductLijst(String trefwoord, int artikelnummer, double vanPrijs, double totPrijs, String plaats, String firma, String email, String doelgroep, String leergebied) {
        pb.filterProductLijst(trefwoord, artikelnummer, vanPrijs, totPrijs, plaats, firma, email, doelgroep, leergebied);
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

    public boolean isNaamUniek(String naam, boolean isWijziging) {

        if (this.getHuidigProduct() != null) {
            return pb.isNaamUniek(naam, this.getHuidigProduct().getNaam(), isWijziging);
        }
        return pb.isNaamUniek(naam, "", isWijziging);
    }

    public void updateDetailvenster() {
        setChanged();
        notifyObservers(huidigProduct);
    }

}
