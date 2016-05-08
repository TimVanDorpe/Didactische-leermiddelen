package domein;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class ProductController extends Observable {

    private Product huidigProduct;
    private ProductBeheer pb;
    private boolean selectionModelEmpty;
    private ObservableList<String> voorlopigeLeergebieden = FXCollections.observableArrayList();
    private ObservableList<String> voorlopigeDoelgroepen = FXCollections.observableArrayList();

    private Product oudProduct;

    private boolean cancelled;

    public Product getOudProduct() {
        return oudProduct;
    }

    public void setOudProduct(Product oudProduct) {
        this.oudProduct = oudProduct;
    }

    public ObservableList<String> getVoorlopigeDoelgroepen() {
        return voorlopigeDoelgroepen;
    }

    public ProductController() {

        pb = new ProductBeheer();

    }

    public List<Product> getProductenLijst() {
        return pb.getProductenLijst();
    }

    public ProductBeheer getPb() {
        return pb;
    }

    public void voegProductToe(URL foto, String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma) {
        //isNaamUniek(naam);
        List<Leergebied> leergebieden = new ArrayList<>();
//        for (String l : gea   atVoorlopigeLeergebieden()) {
//            leergebieden.add(pb.haalLeergebiedUitLijst(naam));
//        }
        leergebieden.add(new Leergebied("Biologie"));

        List<Doelgroep> doelgroepen = new ArrayList<>();
//        for (String l : getVoorlopigeDoelgroepen()) {
//            doelgroepen.add(pb.haalDoelgroepUitLijst(naam));
//        }
        doelgroepen.add(new Doelgroep("Kleuters"));
        Product nieuwProduct = new Product(leergebieden, doelgroepen, firma, foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats, 0);
        pb.voegProductToe(nieuwProduct);
        alleProductenOphalen();
    }

    public Product getHuidigProduct() {
        return huidigProduct;
    }

    public Product getProduct(int artikelnummer) {
        return pb.getProduct(artikelnummer);
    }

    public void wijzigProduct(URL foto, String naam, String omschrijving, int artikelnummer, double prijs, int aantal, String plaats, Firma firma, int aantalOnbeschikbaar) {
        if (!naam.toLowerCase().equals(huidigProduct.getNaam().toLowerCase())) {
            //isNaamUniek(naam);
        }
        List<Leergebied> leergebieden = new ArrayList<>();
        for (String l : getVoorlopigeLeergebieden()) {
            leergebieden.add(pb.haalLeergebiedUitLijst(naam));
        }

        List<Doelgroep> doelgroepen = new ArrayList<>();
        for (String l : getVoorlopigeDoelgroepen()) {
            doelgroepen.add(pb.haalDoelgroepUitLijst(naam));
        }
        pb.wijzigProduct(new Product(leergebieden, doelgroepen, firma, foto, naam, omschrijving, artikelnummer, prijs, aantal, plaats, aantalOnbeschikbaar), huidigProduct);
        alleProductenOphalen();
        setChanged();
        notifyObservers("maakAllesLeegNaWijziging");

        //geefAlleProductenWeer();
    }

    public void verwijderProduct() {
        pb.verwijderProduct(huidigProduct);
        alleProductenOphalen();
    }

    public SortedList<Product> getProductSortedList() {
        //Wrap the FilteredList in a SortedList

        return pb.getSortedList(); //SortedList is unmodifiable
    }

    public void setGeselecteerdProduct(Product product) {

        if (product != null) {
            this.voorlopigeDoelgroepen = FXCollections.observableArrayList(product.getDoelgroepen().stream().map(Doelgroep::getNaam).collect(Collectors.toList()));
            this.voorlopigeLeergebieden = FXCollections.observableArrayList(product.getLeergebieden().stream().map(Leergebied::getNaam).collect(Collectors.toList()));

            //this.huidigProduct = product;
            setChanged();
            notifyObservers(product);
        }

    }

    public ObservableList<Product> zoekOpTrefwoord(String trefwoord) {
        return pb.zoekOpTrefwoord(trefwoord);
    }

    public void filterProductLijst(String trefwoord, int artikelnummer, double vanPrijs, double totPrijs, String plaats, String firma, String email, String doelgroep, String leergebied) {
        pb.filterProductLijst(trefwoord, artikelnummer, vanPrijs, totPrijs, plaats, firma, email, doelgroep, leergebied);
        setChanged();
        notifyObservers();
    }

    public void setSelectionModelEmpty(boolean b) {
        selectionModelEmpty = b;
        notifyObservers();
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

    //LEERGEBIED
//
    public List<Leergebied> getLeergebieden() {
        List<Leergebied> productLijst = pb.getLeergebieden();
        if (huidigProduct != null) {
            for (String l : getVoorlopigeLeergebieden()) {
                productLijst.remove(pb.haalLeergebiedUitLijst(l));
            }
        }
        return productLijst;
    }
//

    public List<Leergebied> getToegevoegdeLeergebieden() {
        if (huidigProduct != null) {
            return huidigProduct.getLeergebieden();
        }
        return null;
    }
//

    public void voegVoorlopigLeergebiedToe(String naam) {
//        Leergebied nieuwLeergebied = pb.haalLeergebiedUitLijst(naam);
//        List<Leergebied> nieuweLijstLeergebieden = huidigProduct.getLeergebieden();
//        nieuweLijstLeergebieden.add(nieuwLeergebied);
        voorlopigeLeergebieden.add(naam);
        //huidigProduct.setLeergebieden(nieuweLijstLeergebieden);

    }

    public void verwijderVoorlopigLeergebied(String naam) {
        voorlopigeLeergebieden.remove(naam);
//        Leergebied verwijderLeergebied = pb.haalLeergebiedUitLijst(naam);
//        List<Leergebied> nieuweLijstLeergebieden = huidigProduct.getLeergebieden();
//        nieuweLijstLeergebieden.remove(verwijderLeergebied);
//        huidigProduct.setLeergebieden(nieuweLijstLeergebieden);

    }

    public ObservableList<String> getVoorlopigeLeergebieden() {
        return voorlopigeLeergebieden;
    }

    public void nieuwLeergebiedToevoegen(String naam) {
        Leergebied leergebied = new Leergebied(naam);
        List<Leergebied> nieuweLijstLeergebieden = huidigProduct.getLeergebieden();
        nieuweLijstLeergebieden.add(leergebied);
        huidigProduct.setLeergebieden(nieuweLijstLeergebieden);
        pb.voegLeergebiedToe(leergebied);
    }

    public List<Doelgroep> getDoelgroepen() {
        List<Doelgroep> productLijst = pb.getDoelgroepen();
        if (huidigProduct != null) {
            for (Doelgroep d : huidigProduct.getDoelgroepen()) {
                productLijst.remove(d);
            }
        }
        return productLijst;
    }
//

    public List<Doelgroep> getToegevoegdeDoelgroepen() {
        if (huidigProduct != null) {
            return huidigProduct.getDoelgroepen();
        }
        return null;
    }
//

    //    public List<Leergebied> getListToegevoegdeLeergebieden() {
//        return pb.getListToegevoegdeLeergebieden();
//    }
//
    public void voegDoelgroepenToeBijHuidigProduct(String naam) {
        Leergebied nieuwLeergebied = pb.haalLeergebiedUitLijst(naam);
        List<Leergebied> nieuweLijstLeergebieden = huidigProduct.getLeergebieden();
        nieuweLijstLeergebieden.add(nieuwLeergebied);
        huidigProduct.setLeergebieden(nieuweLijstLeergebieden);
    }

    public void verwijderDoelgroepHuidigProduct(String naam) {
        Leergebied verwijderLeergebied = pb.haalLeergebiedUitLijst(naam);
        List<Leergebied> nieuweLijstLeergebieden = huidigProduct.getLeergebieden();
        nieuweLijstLeergebieden.remove(verwijderLeergebied);
        huidigProduct.setLeergebieden(nieuweLijstLeergebieden);
    }

    public Product getProductByNaam(String text) {
        return pb.getProductByNaam(text);
    }

    public Product getProductById(int id) {
        return pb.getProductById(id);
    }

    public ObservableList<String> getStringNaamProducten() {
        return StringNaamProducten();
    }

    private ObservableList<String> StringNaamProducten() {
        ObservableList<String> productNamen = FXCollections.observableArrayList();
        for (Product p : getProductenLijst()) {
            productNamen.add(p.getNaam());
        }
        return productNamen;
    }

    public void alleProductenOphalen() {
        pb.alleProductenOphalen();
    }

    public void setCancelled(boolean b) {
        cancelled = b;
    }

    public boolean getCancelled() {
        return cancelled;
    }

    public void setNieuwHuidigProduct(Product product) {

        this.huidigProduct = product;

    }

}
