/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mockito;

/**
 *
 * @author Jens
 */
public class ProductBeheerTest {

    private final ObservableList<Product> producten = FXCollections.observableArrayList();

    private ProductBeheer pb;

    @Before
    public void before() throws Exception {
        pb = new ProductBeheer();
//
//        producten.add(new Product("test", 5));
//        producten.add(new Product("test4", 5));
//        producten.add(new Product("test6", 5));

    }

    @Test
    public void isNaamUniekTest() {
        String naam = "naam1";
        String naam2 = "naam2";
        String naam3 = "naam2";

        pb.isNaamUniek(naam, naam3, true);

        Assert.assertTrue(pb.isNaamUniek(naam, naam3, true));

    }

//    @Test
//    public void getProductOpNaam() {
//
//      //  Product prodvolgensmethode= pb.getProductByNaam("naam");
//
//      //  Assert.assertEquals(prod, prodvolgensmethode );
//
//    }
//    @Test
//    public void zoekenOpTrefwoordTest() {
//        //  create mock
//        ProductBeheer test = Mockito.mock(ProductBeheer.class);
//        Product prodtest = new Product("test", 5);
//        ObservableList<Product> expectedResult = FXCollections.observableArrayList();
//        expectedResult.add(prodtest);
//
//        ObservableList<Product> actualResult = FXCollections.observableArrayList();
//
//        // define return value for method zoekenOpTrefwoord()
//        Mockito.when(test.zoekOpTrefwoord("test")).thenReturn(expectedResult);
//
//        actualResult.add(prodtest);
//
//        Assert.assertEquals(test.zoekOpTrefwoord("test"), actualResult);
//
//    }
//
//    @Test
//    public void zoekenOpNaam() {
//        ProductBeheer test = Mockito.mock(ProductBeheer.class);
//
//        Product prodtest = new Product("test", 5);
//
//
//        // define return value for method zoekenOpTrefwoord()
//        Mockito.when(test.getProductByNaam("test")).thenReturn(prodtest);
//
//
//        Assert.assertEquals(test.getProductByNaam("test"), prodtest);
//
//    }
//    
//    
//
//    
//    
    
    

}
