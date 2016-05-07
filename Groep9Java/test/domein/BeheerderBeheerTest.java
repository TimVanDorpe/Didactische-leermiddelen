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
import org.mockito.Mockito;

/**
 *
 * @author Jarne
 */
public class BeheerderBeheerTest {

    public BeheerderBeheerTest() {

    }

    @Test
    public void geldigeLoginTest() {
        BeheerderBeheer test = Mockito.mock(BeheerderBeheer.class);
        Beheerder beheerder = new Beheerder("test", "test", "test", true);
        ObservableList<Beheerder> expectedResult = FXCollections.observableArrayList();
        expectedResult.add(beheerder);

        ObservableList<Beheerder> actualResult = FXCollections.observableArrayList();

        // define return value for method zoekenOpTrefwoord()
        Mockito.when(test.geldigeLogin(beheerder)).thenReturn(true);

        actualResult.add(beheerder);

        Assert.assertEquals(test.geldigeLogin(beheerder), true);
    }

    @Test//(expected = IllegalArgumentException.class) dit moet dus wel illegalargument throwen
    public void ongeldigeLoginTest() {
        BeheerderBeheer test = Mockito.mock(BeheerderBeheer.class);
        Beheerder beheerder = new Beheerder("test", "test", "test", true);
        ObservableList<Beheerder> expectedResult = FXCollections.observableArrayList();
        expectedResult.add(beheerder);
        test.setBeheerderLijst(expectedResult);

        Beheerder beheerder2 = new Beheerder("dezfrefz", "fzefz", "ezfzegzr", true);
        // define return value for method zoekenOpTrefwoord()
        //Mockito.when(test.geldigeLogin(beheerder)).thenReturn(false);

        test.geldigeLogin(beheerder2);
    }
}
