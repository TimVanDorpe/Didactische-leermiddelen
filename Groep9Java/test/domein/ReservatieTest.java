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
    public void before(){
        r = new Reservatie();
        
        
    }
    
    
    @Test
    public void setGoedeNaamTest(){
        r.("goeieNaam");
        assertEquals("goeieNaam", p.getNaam());
    }
    
    
    @Test(expected=IllegalArgumentException.class)
    public void setNaamSpecialeTekens(){
        p.setNaam("&é");
    }
    
    @Test(expected=NullPointerException.class)
    public void setNaamNull(){
        p.setNaam(null);
    }
    
    
    @Test(expected=IllegalArgumentException.class)
    public void setNaamLegeString(){
        p.setNaam("");
    }
    
    @Test
    public void setGoeiePrijsTest(){
        p.setPrijs(20.0);
        assertEquals(20.0, p.getPrijs(), 1);
    }
    
     @Test(expected=IllegalArgumentException.class)
    public void setPrijsNegatiefTest(){
        p.setPrijs(-20.0);
    }
    
    @Test
    public void setPrijsInt(){
        p.setPrijs(10);
        assertEquals(10.0, p.getPrijs(), 1);
    }
    
    
    
    
    
}
