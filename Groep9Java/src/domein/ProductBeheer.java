/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import util.GenericDao;
import util.GenericDaoJpa;

/**
 *
 * @author Tim
 */
public class ProductBeheer {

    private ObservableList<Product> productenLijst = FXCollections.observableArrayList();
    private Product product;

    private SortedList<Product> sortedList;

    private Product geselecteerdProduct;

    //hier alle comparators
    private final Comparator<Product> byNaam = (p1, p2) -> p1.getNaam().compareToIgnoreCase(p2.getNaam());
    // alle comparators in de juiste volgorde, de volgorde waarop wordt gesorteerd.
    private final Comparator<Product> sortOrder = byNaam;

    private GenericDaoJpa gdj;

    Leergebied mens = new Leergebied("Mens");
    Leergebied maatschapij = new Leergebied("Maatschappij");
    Leergebied geschiedenis = new Leergebied("Geschiedenis");

    private Leergebied[] leergebiedenArray = {mens, maatschapij, geschiedenis};

    private ObservableList<Leergebied> leergebieden;
    private ObservableList<Leergebied> leergebiedenToegevoegd;
    private ObservableList<String> listStringLeergebieden;
    private ObservableList<String> listStringLeergebiedenToegevoegd;

//    public ProductBeheer(EntityManager em, EntityManagerFactory emf) {
//        this(em, emf, new PersistentieController());
//
//    }
//    public ProductBeheer(EntityManager em, EntityManagerFactory emf, PersistentieController pc) {
//        this.persistentieController = pc;
//        InitData data = new InitData(this);
//        data.maakProducten();
//        producten = data.geefProducten();
//        productenLijst = FXCollections.observableArrayList(producten);
//        sortedList = productenLijst.sorted(sortOrder);
//    }
    public ProductBeheer() {

        gdj = new GenericDaoJpa(Product.class);

        ProductData data = new ProductData(this);
        data.maakProducten();

        sortedList = productenLijst.sorted(sortOrder);

        leergebieden = FXCollections.observableArrayList(Arrays.asList(leergebiedenArray));
        leergebiedenToegevoegd = FXCollections.observableArrayList();
        listStringLeergebieden = FXCollections.observableArrayList();
        listStringLeergebiedenToegevoegd = FXCollections.observableArrayList();

    }

    public void setGdj(GenericDaoJpa gdj) {
        this.gdj = gdj;
    }

    public SortedList<Product> getSortedList() {

        //Wrap the FilteredList in a SortedList
        return sortedList; //SortedList is unmodifiable
    }

    public void setSortedList(SortedList<Product> sortedList) {
        this.sortedList = sortedList;
    }

    public void voegProductToe(Product product) {
        gdj.startTransaction();
        productenLijst.add(product);
        gdj.insert(product);
        gdj.commitTransaction();

    }

    public Product getProduct(int artikelnummer) {
        return productenLijst.get(artikelnummer);
    }

    public void wijzigProduct(Product p, Product huidigProduct) {
        gdj.startTransaction();
        Collections.replaceAll(productenLijst, huidigProduct, p);
        gdj.update(p);
        gdj.commitTransaction();

//        for (Product p : productenLijst) {
//            if (p.getArtikelnummer() == product.getArtikelnummer()) {
//                productenLijst.remove(p);
//                productenLijst.add(product);
//            }
//        }
    }

    public void verwijderProduct(Product p) {
        gdj.startTransaction();
        productenLijst.remove(p);
        gdj.delete(p);
        gdj.commitTransaction();
    }

    public ObservableList<Product> zoekOpTrefwoord(String trefwoord) {
        ObservableList<Product> productenLijstMetTrefwoord = FXCollections.observableArrayList();
        List<Product> pp = new ArrayList<>();

        for (Product p : productenLijst) {
            if (p.getNaam().toLowerCase().contains(trefwoord.toLowerCase()) || p.getOmschrijving().toLowerCase().contains(trefwoord.toLowerCase())) {
                pp.add(p);
            }
        }
        productenLijstMetTrefwoord = FXCollections.observableArrayList(pp);
        sortedList = productenLijstMetTrefwoord.sorted(sortOrder);
        return sortedList;
    }

    public void filterProductLijst(String trefwoord, int artikelnummer, double vanPrijs, double totPrijs, String plaats, String firma, String email, String doelgroep, String leergebied) {
        ObservableList<Product> gefilterdeProductenLijst = productenLijst;

        if (!trefwoord.equals("")) {
            gefilterdeProductenLijst.removeIf(p -> !p.getNaam().toLowerCase().contains(trefwoord.toLowerCase()) && !p.getOmschrijving().toLowerCase().contains(trefwoord.toLowerCase()));
        }
        if (artikelnummer != -1) {
            gefilterdeProductenLijst.removeIf(p -> p.getArtikelnummer() != artikelnummer);
        }

        if (vanPrijs > -1) {
            gefilterdeProductenLijst.removeIf(p -> p.getPrijs() < vanPrijs);
        }
        if (totPrijs > -1) {
            gefilterdeProductenLijst.removeIf(p -> p.getPrijs() > totPrijs);
        }
        if (!plaats.equals("")) {
            gefilterdeProductenLijst.removeIf(p -> p.getPlaats() != null && !p.getPlaats().toLowerCase().contains(plaats.toLowerCase()));
        }
        if (!firma.equals("")) {
            gefilterdeProductenLijst.removeIf(p -> p.getFirma().getNaam() != null && !p.getFirma().getNaam().toLowerCase().contains(firma.toLowerCase()));
        }
        if (!email.equals("")) {
            gefilterdeProductenLijst.removeIf(p -> p.getFirma().getEmailContactPersoon() != null && !p.getFirma().getEmailContactPersoon().toLowerCase().contains(email.toLowerCase()));
        }
        if (!doelgroep.equals("")) {

            gefilterdeProductenLijst.removeIf(p -> p.getDoelgroep() != null && !p.getDoelgroep().getNaam().toLowerCase().contains(doelgroep.toLowerCase()));
        }
        //Leergebieden moeten nog gefilterd worden

        sortedList = gefilterdeProductenLijst.sorted(sortOrder);

    }

    void geefAlleProducten() {

        sortedList = productenLijst.sorted(sortOrder);
    }

    //LEERGEBIEDEN
    public ObservableList<Leergebied> getLeergebieden() {
        return leergebieden;
    }

    public ObservableList<Leergebied> getToegevoegd() {
        return leergebiedenToegevoegd;
    }

    public void voegLeergebiedToe(Leergebied naam) {
        leergebiedenToegevoegd.add(naam);
        leergebieden.remove(naam);

    }

    public void verwijderLeergebied(Leergebied naam) {

        leergebieden.add(naam);
        leergebiedenToegevoegd.remove(naam);

    }

    //String methoden
    public ObservableList<String> getStringLeergebieden() {
        for (Leergebied l : leergebieden) {
            String naam = l.getNaam();
            listStringLeergebieden.add(naam);
        }
        return listStringLeergebieden;
    }

    public ObservableList<String> getStringLeergebiedenToegevoegd() {
        for (Leergebied l : leergebiedenToegevoegd) {
            String naam = l.getNaam();
            listStringLeergebiedenToegevoegd.add(naam);
        }
        return listStringLeergebiedenToegevoegd;
    }

    public Leergebied getLeergebiedFromString(String naam) {
        for (Leergebied l : leergebieden) {
            if (l.getNaam().equalsIgnoreCase(naam)) {
                return l;
            }
        }
        return null;
    }

    public Leergebied getLeergebiedToegevoegdFromString(String naam) {
        for (Leergebied l : leergebiedenToegevoegd) {
            if (l.getNaam().equalsIgnoreCase(naam)) {
                return l;
            }
        }
        return null;
    }

    public void voegLeergebiedToeString(String naam) {
        listStringLeergebiedenToegevoegd.add(naam);
        listStringLeergebieden.remove(naam);

    }

    public void verwijderLeergebiedString(String naam) {

        listStringLeergebieden.add(naam);
        listStringLeergebiedenToegevoegd.remove(naam);

    }

    //    public boolean geenToegevoegd() {
//        return leergebiedenToegevoegd.isEmpty();
//
//    }
//
//    public boolean geenLeergebieden() {
//        return leergebieden.isEmpty();
//
//    }
    //NIEUW LEERGEBIED TOEVOEGEN
  public void voegToeAanLeergebieden(Leergebied leergebied){
      leergebieden.add(leergebied);
  }
    
    //EINDE LEERGEBIEDEN
    public boolean isNaamUniek(String naam, String naamGeselecteerdProduct, boolean isWijziging) {
        if (isWijziging) {
            if (!naamGeselecteerdProduct.equals(naam)) { // als het een wijziging is controleer dan niet op de huidig geselecteerde naam
                if (productenLijst.stream().anyMatch(p -> p.getNaam().toLowerCase().equals(naam.toLowerCase()))) {
                    return false;
                    //throw new IllegalArgumentException("Naam moet uniek zijn");
                }
            }

        } else if (productenLijst.stream().anyMatch(p -> p.getNaam().toLowerCase().equals(naam.toLowerCase()))) {
            return false;
        }
        return true;
    }
}
