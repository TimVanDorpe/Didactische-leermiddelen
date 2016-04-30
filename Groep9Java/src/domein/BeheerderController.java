/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Observable;
import javafx.collections.ObservableList;

/**
 *
 * @author Thomas
 */
public class BeheerderController extends Observable {

    private BeheerderBeheer bb;
    private Beheerder aangemeldeBeheerder;
    private Beheerder huidigeBeheerder;
    private boolean selectionModelEmpty;

    public BeheerderController() {
        bb = new BeheerderBeheer();
    }

    public void meldAan(Beheerder beheerder) {
        if (bb.geldigeLogin(beheerder)) {
            this.aangemeldeBeheerder = beheerder;
        }
    }

    public Beheerder getAangemeldeBeheerder() {
        return aangemeldeBeheerder;
    }

    public void setAangemeldeBeheerder(Beheerder aangemeldeBeheerder) {
        this.aangemeldeBeheerder = aangemeldeBeheerder;
    }

    public void logUit() {
        this.aangemeldeBeheerder = null;
    }

    public ObservableList<Beheerder> getBeheerderslijst() {
        return bb.getBeheerderslijst();
    }

    public void setGeselecteerdeBeheerder(Beheerder beh) {
        this.huidigeBeheerder = beh;
        setChanged();
        notifyObservers(beh);
    }

    public void setSelectionModelEmpty(boolean b) {
        selectionModelEmpty = b;
    }

    public boolean getSelectionModelEmpty() {
        return selectionModelEmpty;
    }

    public void wijzigBeheerder(String naam, String email, String wachtwoord) {
        Beheerder nieuweBeheerder = new Beheerder(naam, email, wachtwoord , false);
        bb.wijzigReservatie(nieuweBeheerder, huidigeBeheerder);
        setChanged();
        notifyObservers();
    }

    public void updateDetailVenster() {

        setChanged();
        notifyObservers(huidigeBeheerder);
    }

    public void removeBeheerder() {
        bb.removeBeheerder(huidigeBeheerder);
    }

    public void voegBeheerderToe(String naam, String email, String wachtwoord) {
        bb.voegBeheerderToe(naam, email, wachtwoord) ;
    }

    public ObservableList<Beheerder> zoekenOpBeheerdersNaam(String text) {
      return bb.zoekOpBeheerdersNaam(text);
      
    }
}
