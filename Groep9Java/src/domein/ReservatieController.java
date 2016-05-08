package domein;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        ObservableList<Reservatie> reservatieLijst = FXCollections.observableArrayList();
        rb.haalAlleReservatiesOp();
        for (Reservatie r: rb.getReservatieLijst()){
                    if (r.isNogWeergeven())
                        reservatieLijst.add(r);
            }
        return reservatieLijst;
    }

    public void setReservatieLijst(ObservableList<Reservatie> reservatieLijst) {
        rb.setReservatieLijst(reservatieLijst);
    }

    public void addReservatie(Reservatie r) {
        rb.addReservatie(r);
         setChanged();
        notifyObservers();
    }

    public void removeReservatie() {
        Product huidigProduct =  huidigeReservatie.getGereserveerdProduct();
        List<Reservatie> huidigProductReservaties =  huidigProduct.getReservaties();
         for(Iterator<Reservatie> iter  = huidigProductReservaties.iterator(); iter.hasNext();){
            Reservatie  obj = iter.next();
            if(obj.getId() == huidigeReservatie.getId()){
                iter.remove();
            }
        }
        rb.removeReservatie(huidigeReservatie);
         setChanged();
        notifyObservers();
    }

    public void setGeselecteerdeReservatie(Reservatie res) {
        this.huidigeReservatie = res;
        setChanged();
        notifyObservers(res);
    }


    public void wijzigReservatie(Product product, int aantal, String student, LocalDate startDatum, LocalDate eindDatum, int opTeHalen, int teruggebracht) {


        Reservatie nieuweReservatie = new Reservatie(startDatum, eindDatum, student, product, aantal, opTeHalen, teruggebracht);
        rb.wijzigReservatie(nieuweReservatie, huidigeReservatie);
        removeReservatie();
        setChanged();
        notifyObservers();
    }
    
    public void wijzigAantal(int aantal){ 
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
         ObservableList<Reservatie> reservatieLijstMetTrefwoord = FXCollections.observableArrayList();
        List<Reservatie> rr = new ArrayList<>();

        for (Reservatie r : getReservatieLijst()) {
            if (r.getGereserveerdProduct().getNaam().toLowerCase().contains(productNaam.toLowerCase()) || r.getGereserveerdProduct().getOmschrijving().toLowerCase().contains(productNaam.toLowerCase())) {
                rr.add(r);
            }
        }
        reservatieLijstMetTrefwoord = FXCollections.observableArrayList(rr);

        return reservatieLijstMetTrefwoord;
    }

    public ObservableList<String> getStudentenLijst() {
       return rb.getStudentenLijst();
    }
   

        
    
}
