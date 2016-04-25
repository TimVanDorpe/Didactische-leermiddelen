/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jens
 */
public class ReservatieBeheer {

//    private SortedList<Reservatie> sortedList;
    private ObservableList<Reservatie> reservatieLijst = FXCollections.observableArrayList();

    
//     //hier alle comparators
//    private final Comparator<Reservatie> byNaam = (p1, p2) -> p1.getGereserveerdProduct().getNaam().compareToIgnoreCase(p2.getGereserveerdProduct().getNaam());
//    // alle comparators in de juiste volgorde, de volgorde waarop wordt gesorteerd.
//    private final Comparator<Reservatie> sortOrder = byNaam;
//    
    
    public ReservatieBeheer() {
        ReservatieData data = new ReservatieData(this);
        data.maakReservaties();
        
        
//        sortedList = reservatieLijst.sorted(sortOrder);
        
    }

//     public SortedList<Reservatie> getSortedList() {
//
//        //Wrap the FilteredList in a SortedList
//        return sortedList; //SortedList is unmodifiable
//    }
//
//    public void setSortedList(SortedList<Reservatie> sortedList) {
//        this.sortedList = sortedList;
//    }

    
    
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


    void wijzigReservatie(Reservatie nieuweReservatie, Reservatie huidigeReservatie) {
        huidigeReservatie.setGereserveerdAantal(nieuweReservatie.getGereserveerdAantal());
        huidigeReservatie.setGereserveerdProduct(nieuweReservatie.getGereserveerdProduct());
        huidigeReservatie.setStartDatum(nieuweReservatie.getStartDatum());
        huidigeReservatie.setEindDatum(nieuweReservatie.getEindDatum());
        huidigeReservatie.setGebruiker(nieuweReservatie.getGebruiker());
     
    }

    
    public void wijzigAantal(Reservatie huidigeReservatie, int aantal){
        Reservatie nieuweReservatie = huidigeReservatie;
        nieuweReservatie.setGereserveerdAantal(aantal);
        Collections.replaceAll(reservatieLijst, huidigeReservatie, nieuweReservatie);
    }
    
}
