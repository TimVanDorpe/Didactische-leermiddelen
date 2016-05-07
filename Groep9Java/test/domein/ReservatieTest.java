/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Jens
 */
public class ReservatieTest {

    private Reservatie r;

    @Before
    public void before() {
        r = new Reservatie();
        r.setOpTeHalen(5);
        r.setTeruggebracht(2);
    }

    @Test
    public void setGereserveerdAantalTest() {
        r.setGereserveerdAantal(20);
        assertEquals(20, r.getGereserveerdAantal());
    }
    
    


    @Test(expected = IllegalArgumentException.class)
    public void setGereserveerdAantalTestFout() {
        r.setGereserveerdAantal(3);
        
    }

}
