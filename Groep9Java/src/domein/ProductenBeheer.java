/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import persistentie.PersistentieController;

/**
 *
 * @author Tim
 */
public class ProductenBeheer {

    private ObservableList<Product> productenLijst;
     private FilteredList<Product> filterProductLijst;
    private Product product;
    private List<Product> producten;
    private PersistentieController persistentieController;
    private SortedList<Product> sortedList;

    //hier alle comparators
    private final Comparator<Product> byNaam = (p1, p2) -> p1.getNaam().compareToIgnoreCase(p2.getNaam());
    // alle comparators in de juiste volgorde, de volgorde waarop wordt gesorteerd.
    private final Comparator<Product> sortOrder = byNaam;
    
    
      private EntityManager em;
    private EntityManagerFactory emf;

    public ProductenBeheer(EntityManager em , EntityManagerFactory emf) {
        persistentieController = new PersistentieController();
        producten = persistentieController.geefProducten();
        productenLijst = FXCollections.observableArrayList(producten);
        filterProductLijst = new FilteredList<>(productenLijst, p -> true);
         sortedList = new SortedList<>(filterProductLijst);
        sortedList = productenLijst.sorted(sortOrder);
        this.em = em;
        this.emf = emf;
    }

    public SortedList<Product> getProductSortedList() {
        //Wrap the FilteredList in a SortedList
        return sortedList; //SortedList is unmodifiable
    }

    public void voegProductToe(Product product) {
        em.getTransaction().begin(); 
        productenLijst.add(product);
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

        product.wijzig(product);
        persistentieController.wijzigProduct(product);
        
    }
    
     public void veranderFilter(String naamOfOmschrijving,String leergebied, String doelgroep, Firma firma, String foto,int artikelnummer, double prijs, int aantal, String plaats) {
//        filterProductLijst.setPredicate(product -> {
////            boolean vanMerkLeeg = vanMerk == null || vanMerk.isEmpty();
////            boolean totMerkLeeg = totMerk == null || totMerk.isEmpty();
////                
////            if (vanMerkLeeg && totMerkLeeg) {return true;}
////
////            boolean conditieVanMerk = vanMerkLeeg ? 
////                    false : (auto.getMerk().compareToIgnoreCase(vanMerk) >= 0);
////            boolean conditieTotMerk = totMerkLeeg ? 
////                    false : ((auto.getMerk().compareToIgnoreCase(totMerk) <=0)
////                    || auto.getMerk().toLowerCase().startsWith(totMerk.toLowerCase()));
////
////            if (vanMerkLeeg) {
////                return conditieTotMerk;
////            } else if (totMerkLeeg) {
////                return conditieVanMerk;
////            } else {
////                return (conditieVanMerk && conditieTotMerk);
////            }
//        });
    }
}
