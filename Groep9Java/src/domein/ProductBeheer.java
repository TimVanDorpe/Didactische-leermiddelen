/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.OneToMany;
import persistentie.PersistentieController;

/**
 *
 * @author Tim
 */
public class ProductBeheer {
    
    private ObservableList<Product> productenLijst = FXCollections.observableArrayList();
    private Product product;   
    private List<Product> producten = new ArrayList<>();
    private PersistentieController persistentieController;
    private SortedList<Product> sortedList;

    //hier alle comparators
    private final Comparator<Product> byNaam = (p1, p2) -> p1.getNaam().compareToIgnoreCase(p2.getNaam());
    // alle comparators in de juiste volgorde, de volgorde waarop wordt gesorteerd.
    private final Comparator<Product> sortOrder = byNaam;

    private EntityManager em;
    private EntityManagerFactory emf;
    
    Leergebied mens = new Leergebied("Mens");
    Leergebied maatschapij = new Leergebied("Maatschappij");
    Leergebied geschiedenis = new Leergebied("Geschiedenis");

    private Leergebied[] leergebiedenArray = {mens, maatschapij, geschiedenis};

    private ObservableList<Leergebied> leergebieden;
    private ObservableList<Leergebied> leergebiedenToegevoegd;
    private ObservableList<String> listStringLeergebieden;
    private ObservableList<String> listStringLeergebiedenToegevoegd;


    public ProductBeheer(EntityManager em , EntityManagerFactory emf) {
        this(em,emf,new PersistentieController());
        productenLijst = FXCollections.observableArrayList(producten);
        sortedList = productenLijst.sorted(sortOrder);
    }
    
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
    
    public ProductBeheer(EntityManager em, EntityManagerFactory emf, PersistentieController pc){
        this.em = em;
        this.emf = emf;

         this.persistentieController = pc;
        InitData data = new InitData(this);
        data.maakProducten();   
        
        leergebieden = FXCollections.observableArrayList(Arrays.asList(leergebiedenArray));
        leergebiedenToegevoegd = FXCollections.observableArrayList();
        listStringLeergebieden = FXCollections.observableArrayList();
        listStringLeergebiedenToegevoegd = FXCollections.observableArrayList();
            
        
       
    }

    public SortedList<Product> getProductSortedList() {
        //Wrap the FilteredList in a SortedList
        return sortedList; //SortedList is unmodifiable
    }

    public void voegProductToe(Product product) {
        em.getTransaction().begin();        
        productenLijst.add(product);  
        producten.add(product);
        em.persist(product);        
        em.getTransaction().commit();
        
    }

    public List<Product> geefOverzichtProducten() {

        return producten;
    }

    public Product getProduct(int artikelnummer) {
        return producten.get(artikelnummer);
    }

    public void wijzigProduct(Product product) {

        for (Product p : productenLijst) {
            if (p.getArtikelnummer() == product.getArtikelnummer()) {
                productenLijst.remove(p);
                productenLijst.add(product);
            }
        }

    }

    public ObservableList<Product> zoekOpTrefwoord(String trefwoord) {
        ObservableList<Product> productenLijstMetTrefwoord = FXCollections.observableArrayList();  
        List<Product> pp = new ArrayList<>();
        
       for (Product p : producten)
       {
       if(p.getNaam().contains(trefwoord) || p.getOmschrijving().contains(trefwoord))
       {
       pp.add(p);
       }
       }
       productenLijstMetTrefwoord= FXCollections.observableArrayList(pp);
       return productenLijstMetTrefwoord;
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
    
    
    
    //EINDE LEERGEBIEDEN
}
