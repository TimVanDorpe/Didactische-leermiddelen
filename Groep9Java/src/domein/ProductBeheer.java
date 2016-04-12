/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
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
    
    private ObservableList<Product> productenLijst = FXCollections.observableArrayList();;
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

    public ProductBeheer(EntityManager em , EntityManagerFactory emf) {
        this(em,emf,new PersistentieController());
        productenLijst = FXCollections.observableArrayList(producten);
        sortedList = productenLijst.sorted(sortOrder);
    }
    
    public ProductBeheer(EntityManager em, EntityManagerFactory emf, PersistentieController pc){
        this.em = em;
        this.emf = emf;
        this.persistentieController = pc;
        InitData data = new InitData(this);
        data.maakProducten();        
        
       
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

      for(Product p : productenLijst){
            if(p.getArtikelnummer() == product.getArtikelnummer()){
                productenLijst.remove(p);
                productenLijst.add(product);
            }
        }
        

    }

}
