/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;
import java.time.Month;
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

    }

    @Test
    public void setGereserveerdAantalTest() {
        r.setOpTeHalen(5);
        r.setTeruggebracht(2);
        r.setGereserveerdAantal(20);
        assertEquals(20, r.getGereserveerdAantal());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setGereserveerdAantalTestFout() {
        r.setOpTeHalen(5);
        r.setTeruggebracht(2);
        r.setGereserveerdAantal(3);

    }

    @Test
    public void berekenStatusTestNietAllesTerug() {
        r.setTeruggebracht(5);
        r.setGereserveerdAantal(20);
        r.setOpTeHalen(1);

        r.setStartDatum(LocalDate.of(2016, Month.MAY, 1));
        r.setEindDatum(LocalDate.of(2016, Month.MAY, 6));

        r.berekenStatus();
        assertEquals("Niet alles teruggebracht", r.getStatus());

    }

    @Test
    public void berekenStatusTestKlaarOmOpTeHalen() {
        r.setTeruggebracht(5);
        r.setGereserveerdAantal(20);
        r.setOpTeHalen(20);

        r.setStartDatum(LocalDate.of(2016, Month.MAY, 1));
        r.setEindDatum(LocalDate.of(2016, Month.MAY, 14));

        r.berekenStatus();
        assertEquals("Klaar om op te halen", r.getStatus());

    }

    @Test
    public void berekenStatusTestUitgeleend() {
        r.setTeruggebracht(5);
        r.setGereserveerdAantal(20);
        r.setOpTeHalen(21);

        r.setStartDatum(LocalDate.of(2016, Month.MAY, 1));
        r.setEindDatum(LocalDate.of(2016, Month.MAY, 14));

        r.berekenStatus();
        assertEquals("Uitgeleend", r.getStatus());

    }

    @Test
    public void berekenStatusTestKlaarTeLeggen() {
        r.setTeruggebracht(5);
        r.setGereserveerdAantal(20);
        r.setOpTeHalen(0);

        r.setStartDatum(LocalDate.of(2016, Month.MAY, 10));
        r.setEindDatum(LocalDate.of(2016, Month.MAY, 14));

        r.berekenStatus();
        assertEquals("Klaar te leggen", r.getStatus());

    }

    @Test
    public void setEindatumGoed() {
        r.setStartDatum(LocalDate.of(2016, Month.MAY, 10));
        r.setEindDatum(LocalDate.of(2016, Month.MAY, 14));

        assertEquals(LocalDate.of(2016, Month.MAY, 14), r.getEindDatum());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setEindatumFalse() {
        r.setStartDatum(LocalDate.of(2016, Month.MAY, 15));
        r.setEindDatum(LocalDate.of(2016, Month.MAY, 14));

    }

}
