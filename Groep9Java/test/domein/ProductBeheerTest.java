/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import persistentie.IProductMapper;
import persistentie.PersistentieController;

/**
 *
 * @author Jens
 */
public class ProductBeheerTest {
    
    private ProductBeheer pb;
    private EntityManager em;
    private EntityManagerFactory emf;
    private final String PERSISTENCE_UNIT_NAME = "Groep09";
    
    @Mock
    private IProductMapper pmdummy;
    
    @Before
    public void before()
    {
        MockitoAnnotations.initMocks(this); 
        PersistentieController persistentieController = new PersistentieController();
        
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
        
        
        
        
        
        //persistentieController.setProductMapper(pmdummy);
        ProductBeheer pb = new ProductBeheer(em, emf, persistentieController);
    }

    
    @Test
    public void testMethod1(){
        
    }
    
    
    
    
    /*
    @Test
    public void berekeningLandStatistiek()
    {
        Mockito.when(landMapperDummy.findLand(CODE))
                .thenReturn(new Land(CODE, 10));

        Mockito.when(landMapperDummy.findOppervlakteAlleLanden())
                .thenReturn(100);
        
        LandStatistiek landStatistiek = landService.geefLandStatistiek(CODE);
        
        Land land = landStatistiek.getLand();
        Assert.assertEquals(CODE, land.getCode());
        Assert.assertEquals(10, land.getOppervlakte());
        Assert.assertEquals(0.1, landStatistiek.getVerhouding(), 0.09);
        
        //Verifieert dat de methode één keer opgeroepen is geweest (in dit geval door de LandStatistiek)
        Mockito.verify(landMapperDummy).findLand(CODE);
        Mockito.verify(landMapperDummy).findOppervlakteAlleLanden();
    }

    @Test(expected = IllegalArgumentException.class)
    public void legeCode()
    {
        landService.geefLandStatistiek("");
    }

    @Test(expected = NullPointerException.class)
    public void nullCode()
    {
        landService.geefLandStatistiek(null);
    }

    @Test
    public void landBestaatNiet()
    {
        Mockito.when(landMapperDummy.findLand(CODE)).thenReturn(null);
        
        Mockito.when(landMapperDummy.findOppervlakteAlleLanden()).thenReturn(100);
        
        Assert.assertNull(landService.geefLandStatistiek(CODE));
        
        Mockito.verify(landMapperDummy).findLand(CODE);
    }
    
    */
}
