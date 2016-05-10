package domein;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import util.GenericDaoJpa;

/**
 *
 * @author Jens
 */
public class ReservatieBeheer {

    private ObservableList<Reservatie> reservatieLijst = FXCollections.observableArrayList();
    private SortedList<Reservatie> sortedList;

    private final Comparator<Reservatie> byStartDatum = (r1, r2) -> r1.getStartDatum().compareTo(r2.getStartDatum());

    private final Comparator<Reservatie> sortOrder = byStartDatum;

    private GenericDaoJpa gdj;

    public ReservatieBeheer() {
        gdj = new GenericDaoJpa(Reservatie.class);
        sortedList = reservatieLijst.sorted(sortOrder);

    }

    public SortedList<Reservatie> getSortedList() {

        //Wrap the FilteredList in a SortedList
        //sortedList = reservatieLijst.sorted(sortOrder);
        return sortedList; //SortedList is unmodifiable
    }

    public void setSortedList(SortedList<Reservatie> sortedList) {
        this.sortedList = sortedList;
    }
//
//    public ObservableList<Reservatie> zoekOpMateriaalNaam(String productNaam)
//    {
//    ObservableList<Reservatie> reservatieLijstMetTrefwoord = FXCollections.observableArrayList();
//        List<Reservatie> rr = new ArrayList<>();
//
//        for (Reservatie r : reservatieLijst) {
//            if (r.getGereserveerdProduct().getNaam().toLowerCase().contains(productNaam.toLowerCase()) || r.getGereserveerdProduct().getOmschrijving().toLowerCase().contains(productNaam.toLowerCase())) {
//                rr.add(r);
//            }
//        }
//        reservatieLijstMetTrefwoord = FXCollections.observableArrayList(rr);
//        sortedList = reservatieLijstMetTrefwoord.sorted(sortOrder);
//        return sortedList;
//    
//    }

    public ObservableList<Reservatie> getReservatieLijst() {
        return getSortedList();
    }

    public void setReservatieLijst(ObservableList<Reservatie> reservatieLijst) {
        this.reservatieLijst = reservatieLijst;
    }

    public void addReservatie(Reservatie r) {
        gdj.startTransaction();
        reservatieLijst.add(r);
        gdj.insert(r);
        gdj.commitTransaction();
    }

    public void removeReservatie(Reservatie r) {
        gdj.startTransaction();
        reservatieLijst.remove(r);
        gdj.delete(r);
        gdj.commitTransaction();
    }

    void wijzigReservatie(Product product, int aantal, String student, LocalDate startDatum, LocalDate eindDatum, int opTeHalen, int teruggebracht, Reservatie huidigeReservatie) {

        gdj.startTransaction();       
        huidigeReservatie.setGereserveerdAantal(aantal);
        huidigeReservatie.setOpTeHalen(opTeHalen);
        huidigeReservatie.setTeruggebracht(teruggebracht);
        huidigeReservatie.setGereserveerdProduct(product);
        huidigeReservatie.setGebruiker(student);
        huidigeReservatie.setStartDatum(startDatum);
        huidigeReservatie.setEindDatum(eindDatum);       
        gdj.update(huidigeReservatie); 
        huidigeReservatie.berekenStatus();
        gdj.update(huidigeReservatie);         
        gdj.commitTransaction();
        
       

    }

    public void wijzigAantal(Reservatie huidigeReservatie, int aantal) {
        Reservatie nieuweReservatie = huidigeReservatie;
        nieuweReservatie.setGereserveerdAantal(aantal);
        Collections.replaceAll(reservatieLijst, huidigeReservatie, nieuweReservatie);
    }

    ObservableList<String> getStudentenLijst() {
        ObservableList<String> studenten = FXCollections.observableArrayList();
        for (Reservatie r : reservatieLijst) {
            if (!(studenten.contains(r.getGebruiker()))) {
                studenten.add(r.getGebruiker());
            }
        }
        return studenten;
    }

    public void haalAlleReservatiesOp() {
        setReservatieLijst(FXCollections.observableArrayList(gdj.findAll()));
        setSortedList(reservatieLijst.sorted(sortOrder));
    }

}
