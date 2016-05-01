/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author Jens
 */
public class ReservatieController extends Observable {

    private ReservatieBeheer rb;
    private Reservatie huidigeReservatie;

    public Reservatie getHuidigeReservatie() {
        return huidigeReservatie;
    }

    public void setHuidigeReservatie(Reservatie huidigeReservatie) {
        this.huidigeReservatie = huidigeReservatie;
    }
    private boolean selectionModelEmpty;

    public ReservatieController() {
        rb = new ReservatieBeheer();
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

//    public ObservableList<Reservatie> getReservatieSortedList() {
//
//        return rb.getSortedList(); //SortedList is unmodifiable
//
//    }

    public void wijzigReservatie(Product product, int aantal, String student, LocalDate startDatum, LocalDate eindDatum, int opTeHalen, int teruggebracht) {

//        GregorianCalendar start = new GregorianCalendar(Integer.parseInt(startDatum.substring(0, 4)),
//                Integer.parseInt(startDatum.substring(5, 7)), Integer.parseInt(startDatum.substring(8, 10),
//                Integer.parseInt(startDatum.substring(11, 13))));
//        GregorianCalendar eind = new GregorianCalendar(Integer.parseInt(eindDatum.substring(0, 4)),
//                Integer.parseInt(eindDatum.substring(5, 7)), Integer.parseInt(eindDatum.substring(8, 10),
//                Integer.parseInt(eindDatum.substring(11, 13))));


        Reservatie nieuweReservatie = new Reservatie(startDatum, eindDatum, student, product, aantal, opTeHalen, teruggebracht);
        rb.wijzigReservatie(nieuweReservatie, huidigeReservatie);
        setChanged();
        notifyObservers();
    }
    
    public void wijzigAantal(int aantal){ // VOOR DEMO
        rb.wijzigAantal(huidigeReservatie, aantal);
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
    public ObservableList<Reservatie> zoekOpMateriaalNaam(String productNaam)
    {
        return rb.zoekOpMateriaalNaam(productNaam);
    }

    public ObservableList<String> getStudentenLijst() {
       return rb.getStudentenLijst();
    }

   

        
    
}
