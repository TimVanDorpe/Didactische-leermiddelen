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
import javafx.collections.transformation.SortedList;
import persistentie.PersistentieController;

/**
 *
 * @author Tim
 */
public class ProductenBeheer {

    private ObservableList<Product> productenLijst;
    private Product product;
    private List<Product> producten;
    private PersistentieController persistentieController;
    private SortedList<Product> sortedList;

    //hier alle comparators
    private final Comparator<Product> byNaam = (p1, p2) -> p1.getNaam().compareToIgnoreCase(p2.getNaam());
    // alle comparators in de juiste volgorde, de volgorde waarop wordt gesorteerd.
    private final Comparator<Product> sortOrder = byNaam;

    public ProductenBeheer() {
        persistentieController = new PersistentieController();
        producten = persistentieController.geefProducten();
        productenLijst = FXCollections.observableArrayList(producten);
        sortedList = productenLijst.sorted(sortOrder);
    }

    public SortedList<Product> getProductSortedList() {
        //Wrap the FilteredList in a SortedList
        return sortedList; //SortedList is unmodifiable
    }

    public void voegProductToe(Product product) {

        productenLijst.add(product);
    }

    public List<Product> geefOverzichtProducten() {

        return producten;
    }

    public Product getProduct(int artikelnummer) {
        return producten.get(artikelnummer);
    }

    public void wijzigProduct(Product product) {

        product.wijzig(product);

    }

}
