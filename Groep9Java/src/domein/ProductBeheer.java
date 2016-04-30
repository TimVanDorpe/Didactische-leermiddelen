/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
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

    private GenericDaoJpa gdjProduct;
    private GenericDaoJpa gdjLeergebied;
    private GenericDaoJpa gdjDoelgroep;

    private List<Leergebied> leergebiedenLijst = new ArrayList<Leergebied>();

    private List<Doelgroep> doelgroepenLijst = new ArrayList<Doelgroep>();

//    private ObservableList<Leergebied> leergebieden;
//    
//    private ObservableList<Doelgroep> doelgroepen;
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

        gdjProduct = new GenericDaoJpa(Product.class);
        gdjLeergebied = new GenericDaoJpa(Leergebied.class);
        gdjDoelgroep = new GenericDaoJpa(Doelgroep.class);

        ProductData data = new ProductData(this);
        try {
            data.maakProducten();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProductBeheer.class.getName()).log(Level.SEVERE, null, ex);
        }

        sortedList = productenLijst.sorted(sortOrder);

//        leergebieden = FXCollections.observableArrayList(Arrays.asList(leergebiedenArray));
//      
//
//        doelgroepen = FXCollections.observableArrayList(Arrays.asList(doelgroepenArray));
//        
//        leergebiedenLijst.add(new Leergebied("Mens"));
//        leergebiedenLijst.add(new Leergebied("Maatschappij"));
//        leergebiedenLijst.add(new Leergebied("Geschiedenis"));
//
//        leergebiedenLijst = gdjProduct.findAll();
//        doelgroepenLijst = gdjProduct.findAll();
//        
//        doelgroepenLijst.add(new Doelgroep("Kleuters"));
//        doelgroepenLijst.add(new Doelgroep("Lagere school"));
//        doelgroepenLijst.add(new Doelgroep("Hoger onderwijs"));
    }

    public ObservableList<Product> getProductenLijst() {
        return productenLijst;
    }

    public void setGdjProduct(GenericDaoJpa gdjProduct) {
        this.gdjProduct = gdjProduct;
    }

    public SortedList<Product> getSortedList() {
        sortedList = productenLijst.sorted(sortOrder);
        //Wrap the FilteredList in a SortedList
        return sortedList; //SortedList is unmodifiable
    }

    public void setSortedList(SortedList<Product> sortedList) {
        this.sortedList = sortedList;
    }

    public void voegProductToe(Product product) {
        gdjProduct.startTransaction();
        productenLijst.add(product);
        gdjProduct.insert(product);
        gdjProduct.commitTransaction();

    }

    public Product getProduct(int artikelnummer) {
        return productenLijst.get(artikelnummer);
    }

    public void wijzigProduct(Product p, Product huidigProduct) {
        gdjProduct.startTransaction();
        productenLijst.remove(huidigProduct);
        productenLijst.add(p);
//        gdjProduct.delete(huidigProduct);
//        gdjProduct.insert(p);
        //Collections.replaceAll(productenLijst, huidigProduct, p);
        //id mag niet vervangen worden.
        huidigProduct.setAantal(p.getAantal());
        huidigProduct.setArtikelnummer(p.getArtikelnummer());
        //huidigProduct.setDoelgroepen(p.getDoelgroepen());
        huidigProduct.setFirma(p.getFirma());
       //huidigProduct.setLeergebieden(p.getLeergebieden());
        huidigProduct.setOmschrijving(p.getOmschrijving());
        huidigProduct.setPlaats(p.getPlaats());
        huidigProduct.setPrijs(p.getPrijs());
        huidigProduct.setNaam(p.getNaam());
        huidigProduct.setFoto(p.getFoto());
        huidigProduct.setLeergebieden(p.getLeergebieden());
        huidigProduct.setDoelgroepen(p.getDoelgroepen());
        gdjProduct.update(huidigProduct);
        gdjProduct.commitTransaction();
       
    }

    public void verwijderProduct(Product p) {
        gdjProduct.startTransaction();
        productenLijst.remove(p);
        gdjProduct.delete(p);
        gdjProduct.commitTransaction();
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

    

    //LEERGEBIEDEN--------------------------------------------
    public List<Leergebied> getLeergebieden() {//returnt linkse list
        return  gdjLeergebied.findAll();
    }

    public Leergebied haalLeergebiedUitLijst(String naam) {
        for (Leergebied l : getLeergebieden()) {
            if (l.getNaam().equalsIgnoreCase(naam)) {
                return l;
            }
        }
        return null;
    }

    public void voegLeergebiedToe(Leergebied naam) {
        leergebiedenLijst.add(naam);
    }

    //LEERGEBIEDEN--------------------------------------------
    public List<Doelgroep> getDoelgroepen() {//returnt linkse list
        return gdjDoelgroep.findAll();
    }

    public Doelgroep haalDoelgroepUitLijst(String naam) {
        for (Doelgroep d : getDoelgroepen()) {
            if (d.getNaam().equalsIgnoreCase(naam)) {
                return d;
            }
        }
        return null;
    }

//

//    public ObservableList<Leergebied> getToegevoegdeLeergebieden() {//returnt rechtse list
//        return leergebiedenRechts;
//    }
//
//    public List<Leergebied> getListToegevoegdeLeergebieden() {//Zet toegevoegde om naar gewone list
//        List<Leergebied> listToegevoegd = new ArrayList<>(getToegevoegdeLeergebieden());
//        return listToegevoegd;
//    }
//
//    public void voegLeergebiedToe(Leergebied naam) {//send right
//        leergebiedenRechts.add(naam);
//        leergebiedenLinks.remove(naam);
//
//    }
//
//    public void verwijderLeergebied(Leergebied naam) {//send left
//
//        leergebiedenLinks.add(naam);
//        leergebiedenRechts.remove(naam);
//
//    }
//    //String methoden
//    public ObservableList<String> getStringLeergebieden() {
//        for (Leergebied l : leergebiedenLinks) {
//            String naam = l.getNaam();
//            stringLeergebiedenLinks.add(naam);
//        }
//        return stringLeergebiedenLinks;
//    }
//
//    public ObservableList<String> getStringLeergebiedenToegevoegd() {
//        for (Leergebied l : leergebiedenRechts) {
//            String naam = l.getNaam();
//            stringLeergebiedenRechts.add(naam);
//        }
//        return stringLeergebiedenRechts;
//    }
//
//    public Leergebied getLeergebiedFromString(String naam) {
//        for (Leergebied l : leergebiedenLinks) {
//            if (l.getNaam().equalsIgnoreCase(naam)) {
//                return l;
//            }
//        }
//        return null;
//    }
//
//    public Leergebied getLeergebiedToegevoegdFromString(String naam) {
//        for (Leergebied l : leergebiedenRechts) {
//            if (l.getNaam().equalsIgnoreCase(naam)) {
//                return l;
//            }
//        }
//        return null;
//    }
//
//    public void voegLeergebiedToeString(String naam) {
//        stringLeergebiedenRechts.add(naam);
//        stringLeergebiedenLinks.remove(naam);
//
//    }
//
//    public void verwijderLeergebiedString(String naam) {
//
//        stringLeergebiedenLinks.add(naam);
//        stringLeergebiedenRechts.remove(naam);
//
//    }
//
//    //NIEUW LEERGEBIED TOEVOEGEN
//    public void voegNieuwToeAanLeergebieden(Leergebied leergebied) {
//        leergebiedenLinks.add(leergebied);
//    }
//
//    //EINDE LEERGEBIEDEN--------------------------------------------
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

//    //DOELGROEPEN----------------------------------------------------
//    public ObservableList<Doelgroep> getDoelgroepen() {
//        return doelgroepen;
//    }
//
//    ObservableList<Doelgroep> getToegevoegdeDoelgroepen() {
//        return doelgroepenRechts;
//    }
//
//    List<Doelgroep> getListToegevoegdeDoelgroepen() {
//        List<Doelgroep> listToegevoegd = new ArrayList<>(getToegevoegdeDoelgroepen());
//        return listToegevoegd;
//    }
//
//    void voegDoelgroepToe(Doelgroep doelgroep) {
//        doelgroepenRechts.add(doelgroep);
//        doelgroepenLinks.remove(doelgroep);
//    }
//
//    void verwijderDoelgroep(Doelgroep doelgroep) {
//        doelgroepenLinks.add(doelgroep);
//        doelgroepenRechts.remove(doelgroep);
//
//    }
//
//    //string doelgroepen
//    ObservableList<String> getStringDoelgroepen() {
//        for (Doelgroep l : doelgroepenLinks) {
//            String naam = l.getNaam();
//            stringDoelgroepenLinks.add(naam);
//        }
//        return stringDoelgroepenLinks;
//    }
//
//    Doelgroep getDoelgroepFromString(String naam) {
//        for (Doelgroep l : doelgroepenLinks) {
//            if (l.getNaam().equalsIgnoreCase(naam)) {
//                return l;
//            }
//        }
//        return null;
//    }
//
//    Doelgroep getDoelgroepToegevoegdFromString(String selectedItem) {
//        for (Doelgroep l : doelgroepenRechts) {
//            if (l.getNaam().equalsIgnoreCase(selectedItem)) {
//                return l;
//            }
//        }
//        return null;
//    }
//
//    ObservableList<String> getStringToegevoegdeDoelgroepen() {
//        for (Doelgroep l : doelgroepenRechts) {
//            String naam = l.getNaam();
//            stringDoelgroepenRechts.add(naam);
//        }
//        return stringDoelgroepenRechts;
//    }
//
//    void voegDoelgroepToeString(String naam) {
//        stringDoelgroepenRechts.add(naam);
//        stringDoelgroepenLinks.remove(naam);
//    }
//
//    void verwijderDoelgroepString(String selectedItem) {
//        stringDoelgroepenLinks.add(selectedItem);
//        stringDoelgroepenRechts.remove(selectedItem);
//    }
//
//    //EINDEDOELGROEPEN--------------------------------------------------------

    Product getProductByNaam(String text) {
        for (Product p : productenLijst)
        {
        if(p.getNaam().equalsIgnoreCase(text))
        {return p;}
        }
       return null;
     
    }
}