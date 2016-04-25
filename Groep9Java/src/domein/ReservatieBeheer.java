/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

/**
 *
 * @author Jens
 */
public class ReservatieBeheer {

//    private SortedList<Reservatie> sortedList;
    private ObservableList<Reservatie> reservatieLijst = FXCollections.observableArrayList();
    private SortedList<Reservatie> sortedList;
 
    private final Comparator<Reservatie> byProdcutNaam = (r1, r2) -> r1.getGereserveerdProduct().getNaam().compareToIgnoreCase(r2.getGereserveerdProduct().getNaam());
 
    private final Comparator<Reservatie> sortOrder = byProdcutNaam;
    

    
    public ReservatieBeheer() {
        ReservatieData data = new ReservatieData(this);
        data.maakReservaties();
        sortedList = reservatieLijst.sorted(sortOrder);
        
        
    }

    public SortedList<Reservatie> getSortedList() {

      //Wrap the FilteredList in a SortedList
       return sortedList; //SortedList is unmodifiable
  }

    public void setSortedList(SortedList<Reservatie> sortedList) {
        this.sortedList = sortedList;
   }

    public ObservableList<Reservatie> zoekOpMateriaalNaam(String productNaam)
    {
    ObservableList<Reservatie> reservatieLijstMetTrefwoord = FXCollections.observableArrayList();
        List<Reservatie> rr = new ArrayList<>();

        for (Reservatie r : reservatieLijst) {
            if (r.getGereserveerdProduct().getNaam().toLowerCase().contains(productNaam.toLowerCase()) || r.getGereserveerdProduct().getOmschrijving().toLowerCase().contains(productNaam.toLowerCase())) {
                rr.add(r);
            }
        }
        reservatieLijstMetTrefwoord = FXCollections.observableArrayList(rr);
        sortedList = reservatieLijstMetTrefwoord.sorted(sortOrder);
        return sortedList;
    
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
