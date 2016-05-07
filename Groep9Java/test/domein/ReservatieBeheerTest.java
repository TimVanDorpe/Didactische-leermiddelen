/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;
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
public class ReservatieBeheerTest {

   // private final ObservableList<Product> producten = FXCollections.observableArrayList();

    private ReservatieBeheer rb;

    @Before
    public void before() throws Exception {
        rb = new ReservatieBeheer();

        Product product1 = new Product("test1", 5);
        Product product2 = new Product("test2", 5);
        Product product3 = new Product("test3", 5);
       
       // Reservatie reservatie = new Reservatie(LocalDate.MAX, LocalDate.MIN, gebruiker, product3, 0, 0, 0)
        
        
    }



//    @Test
//    public void getProductOpNaam() {
//
//      //  Product prodvolgensmethode= pb.getProductByNaam("naam");
//
//      //  Assert.assertEquals(prod, prodvolgensmethode );
//
//    }
    @Test
    public void zoekenOpTrefwoordTest() {
        //  create mock
        ProductBeheer test = Mockito.mock(ProductBeheer.class);
        Product prodtest = new Product("test", 5);
        ObservableList<Product> expectedResult = FXCollections.observableArrayList();
        expectedResult.add(prodtest);

        ObservableList<Product> actualResult = FXCollections.observableArrayList();

        // define return value for method zoekenOpTrefwoord()
        Mockito.when(test.zoekOpTrefwoord("test")).thenReturn(expectedResult);

        actualResult.add(prodtest);

        Assert.assertEquals(test.zoekOpTrefwoord("test"), actualResult);

    }

    @Test
    public void zoekenOpNaam() {
        ProductBeheer test = Mockito.mock(ProductBeheer.class);

        Product prodtest = new Product("test", 5);


        // define return value for method zoekenOpTrefwoord()
        Mockito.when(test.getProductByNaam("test")).thenReturn(prodtest);


        Assert.assertEquals(test.getProductByNaam("test"), prodtest);

    }
    
    

    
    
    

}
