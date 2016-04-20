/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Observable;
import javafx.collections.ObservableList;

/**
 *
 * @author Jens
 */
public class ReservatieController extends Observable {

    
    private ProductController pc;
    private ReservatieBeheer rb;
    private Reservatie huidigeReservatie;
    private boolean selectionModelEmpty;

    public ReservatieController(ProductController pc) {
        rb = new ReservatieBeheer();
        this.pc = pc;
    }

    public ObservableList<Reservatie> getReservatieLijst() {
        return rb.getReservatieLijst();
    }

    public void setReservatieLijst(ObservableList<Reservatie> reservatieLijst) {
        rb.setReservatieLijst(reservatieLijst);
    }

    public void addReservatie(Reservatie r) {
        rb.addReservatie(r);
    }

    public void removeReservatie() {
        rb.removeReservatie(huidigeReservatie);
    }

    public void setGeselecteerdeReservatie(Reservatie res) {
        this.huidigeReservatie = res;
        setChanged();
        notifyObservers(res);
    }

    public ObservableList<Reservatie> getReservatieSortedList() {

        return rb.getSortedList(); //SortedList is unmodifiable


    }
    
    public void wijzigReservatie(String product, int aantal, String student, String startDatum, String eindDatum) {
        
        Calendar start = new GregorianCalendar(Integer.parseInt(startDatum.substring(0, 4)), 
                Integer.parseInt(startDatum.substring(5,7)), Integer.parseInt(startDatum.substring(8, 10),
                        Integer.parseInt(startDatum.substring(11,13))));
        Calendar eind = new GregorianCalendar(Integer.parseInt(eindDatum.substring(0, 4)), 
                Integer.parseInt(eindDatum.substring(5,7)), Integer.parseInt(eindDatum.substring(8, 10),
                        Integer.parseInt(eindDatum.substring(11,13))));
        
            Product prod = pc.getProductenLijst().stream().filter(p->p.getNaam().toLowerCase().equals(product)).findAny().get();
                
                
                //productenLijst.stream().anyMatch(p -> p.getNaam().toLowerCase().equals(naam.toLowerCase()
        
        Reservatie nieuweReservatie = new Reservatie(start, eind, student, prod, aantal);
                
                rb.wijzigReservatie(nieuweReservatie, huidigeReservatie);
        setChanged();
        notifyObservers();
    }
    
    
    public void updateDetailvenster() {
        setChanged();
        notifyObservers(huidigeReservatie);
    }

    public void setSelectionModelEmpty(boolean b) {
        selectionModelEmpty = b;
    }

    public boolean getSelectionModelEmpty() {
        return selectionModelEmpty;
    }
}
