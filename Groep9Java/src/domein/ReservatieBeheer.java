/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

/**
 *
 * @author Jens
 */
public class ReservatieBeheer {

    private SortedList<Reservatie> sortedList;
    private ObservableList<Reservatie> reservatieLijst = FXCollections.observableArrayList();

    public ReservatieBeheer() {

    }

    public ObservableList<Reservatie> getReservatieLijst() {
        return reservatieLijst;
    }

    public void setReservatieLijst(ObservableList<Reservatie> reservatieLijst) {
        this.reservatieLijst = reservatieLijst;
    }

    public void addReservatie(Reservatie r) {
        reservatieLijst.add(r);
    }

    public void removeReservatie(Reservatie r) {
        reservatieLijst.remove(r);
    }

    ObservableList<Reservatie> getSortedList() {
        return sortedList;
    }

    void wijzigReservatie(Reservatie nieuweReservatie, Reservatie huidigeReservatie) {
        huidigeReservatie.setGereserveerdAantal(nieuweReservatie.getGereserveerdAantal());
        huidigeReservatie.setGereserveerdProduct(nieuweReservatie.getGereserveerdProduct());
        huidigeReservatie.setStartDatum(nieuweReservatie.getStartDatum());
        huidigeReservatie.setEindDatum(nieuweReservatie.getEindDatum());
        huidigeReservatie.setGebruiker(nieuweReservatie.getGebruiker());
    }

}
