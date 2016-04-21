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
    //private Product product;

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

    Doelgroep kleuters = new Doelgroep("Kleuters");
    Doelgroep lager = new Doelgroep("Lagere onderwijs");
    Doelgroep hoger = new Doelgroep("Hoger onderwijs");

    private Doelgroep[] doelgroepenArray = {kleuters, lager, hoger};

    private ObservableList<Leergebied> leergebiedenLinks;
    private ObservableList<Leergebied> leergebiedenRechts;
    private ObservableList<String> stringLeergebiedenLinks;
    private ObservableList<String> stringLeergebiedenRechts;

    private ObservableList<Doelgroep> doelgroepenLinks;
    private ObservableList<Doelgroep> doelgroepenRechts;
    private ObservableList<String> stringDoelgroepenLinks;
    private ObservableList<String> stringDoelgroepenRechts;

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

        leergebiedenLinks = FXCollections.observableArrayList(Arrays.asList(leergebiedenArray));
        leergebiedenRechts = FXCollections.observableArrayList();
        stringLeergebiedenLinks = FXCollections.observableArrayList();
        stringLeergebiedenRechts = FXCollections.observableArrayList();

        doelgroepenLinks = FXCollections.observableArrayList(Arrays.asList(doelgroepenArray));
        doelgroepenRechts = FXCollections.observableArrayList();
        stringDoelgroepenLinks = FXCollections.observableArrayList();
        stringDoelgroepenRechts = FXCollections.observableArrayList();

    }

    public ObservableList<Product> getProductenLijst() {
        return productenLijst;
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
        //Collections.replaceAll(productenLijst, huidigProduct, p);
        //id mag niet vervangen worden.
        huidigProduct.setAantal(p.getAantal());
        huidigProduct.setArtikelnummer(p.getArtikelnummer());
        huidigProduct.setDoelgroep(p.getDoelgroep());
        huidigProduct.setFirma(p.getFirma());
        huidigProduct.setLeergebied(p.getLeergebied());
        huidigProduct.setOmschrijving(p.getOmschrijving());
        huidigProduct.setPlaats(p.getPlaats());
        huidigProduct.setPrijs(p.getPrijs());
        gdj.update(p);
        gdj.commitTransaction();
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
//        if (!doelgroep.equals("")) {
//
//            gefilterdeProductenLijst.removeIf(p -> p.getDoelgroep() != null && !p.getDoelgroep().getNaam().toLowerCase().contains(doelgroep.toLowerCase()));
//        }
        //Leergebieden moeten nog gefilterd worden

        sortedList = gefilterdeProductenLijst.sorted(sortOrder);

    }

    void geefAlleProducten() {

        sortedList = productenLijst.sorted(sortOrder);
    }

    //LEERGEBIEDEN--------------------------------------------
    public ObservableList<Leergebied> getLeergebieden() {//returnt linkse list
        return leergebiedenLinks;
    }

    public ObservableList<Leergebied> getToegevoegdeLeergebieden() {//returnt rechtse list
        return leergebiedenRechts;
    }

    public List<Leergebied> getListToegevoegdeLeergebieden() {//Zet toegevoegde om naar gewone list
        List<Leergebied> listToegevoegd = new ArrayList<>(getToegevoegdeLeergebieden());
        return listToegevoegd;
    }

    public void voegLeergebiedToe(Leergebied naam) {//send right
        leergebiedenRechts.add(naam);
        leergebiedenLinks.remove(naam);

    }

    public void verwijderLeergebied(Leergebied naam) {//send left

        leergebiedenLinks.add(naam);
        leergebiedenRechts.remove(naam);

    }

    //String methoden
    public ObservableList<String> getStringLeergebieden() {
        for (Leergebied l : leergebiedenLinks) {
            String naam = l.getNaam();
            stringLeergebiedenLinks.add(naam);
        }
        return stringLeergebiedenLinks;
    }

    public ObservableList<String> getStringLeergebiedenToegevoegd() {
        for (Leergebied l : leergebiedenRechts) {
            String naam = l.getNaam();
            stringLeergebiedenRechts.add(naam);
        }
        return stringLeergebiedenRechts;
    }

    public Leergebied getLeergebiedFromString(String naam) {
        for (Leergebied l : leergebiedenLinks) {
            if (l.getNaam().equalsIgnoreCase(naam)) {
                return l;
            }
        }
        return null;
    }

    public Leergebied getLeergebiedToegevoegdFromString(String naam) {
        for (Leergebied l : leergebiedenRechts) {
            if (l.getNaam().equalsIgnoreCase(naam)) {
                return l;
            }
        }
        return null;
    }

    public void voegLeergebiedToeString(String naam) {
        stringLeergebiedenRechts.add(naam);
        stringLeergebiedenLinks.remove(naam);

    }

    public void verwijderLeergebiedString(String naam) {

        stringLeergebiedenLinks.add(naam);
        stringLeergebiedenRechts.remove(naam);

    }

    //NIEUW LEERGEBIED TOEVOEGEN
    public void voegNieuwToeAanLeergebieden(Leergebied leergebied) {
        leergebiedenLinks.add(leergebied);
    }

    //EINDE LEERGEBIEDEN--------------------------------------------
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

    //DOELGROEPEN----------------------------------------------------
    ObservableList<Doelgroep> getDoelgroepen() {
        return doelgroepenLinks;
    }

    ObservableList<Doelgroep> getToegevoegdeDoelgroepen() {
        return doelgroepenRechts;
    }

    List<Doelgroep> getListToegevoegdeDoelgroepen() {
        List<Doelgroep> listToegevoegd = new ArrayList<>(getToegevoegdeDoelgroepen());
        return listToegevoegd;
    }

    void voegDoelgroepToe(Doelgroep doelgroep) {
        doelgroepenRechts.add(doelgroep);
        doelgroepenLinks.remove(doelgroep);
    }

    void verwijderDoelgroep(Doelgroep doelgroep) {
        doelgroepenLinks.add(doelgroep);
        doelgroepenRechts.remove(doelgroep);

    }

    //string doelgroepen
    ObservableList<String> getStringDoelgroepen() {
        for (Doelgroep l : doelgroepenLinks) {
            String naam = l.getNaam();
            stringDoelgroepenLinks.add(naam);
        }
        return stringDoelgroepenLinks;
    }

    Doelgroep getDoelgroepFromString(String naam) {
        for (Doelgroep l : doelgroepenLinks) {
            if (l.getNaam().equalsIgnoreCase(naam)) {
                return l;
            }
        }
        return null;
    }

    Doelgroep getDoelgroepToegevoegdFromString(String selectedItem) {
        for (Doelgroep l : doelgroepenRechts) {
            if (l.getNaam().equalsIgnoreCase(selectedItem)) {
                return l;
            }
        }
        return null;
    }

    ObservableList<String> getStringToegevoegdeDoelgroepen() {
        for (Doelgroep l : doelgroepenRechts) {
            String naam = l.getNaam();
            stringDoelgroepenRechts.add(naam);
        }
        return stringDoelgroepenRechts;
    }

    void voegDoelgroepToeString(String naam) {
        stringDoelgroepenRechts.add(naam);
        stringDoelgroepenLinks.remove(naam);
    }

    void verwijderDoelgroepString(String selectedItem) {
        stringDoelgroepenLinks.add(selectedItem);
        stringDoelgroepenRechts.remove(selectedItem);
    }

    //EINDEDOELGROEPEN--------------------------------------------------------
}
