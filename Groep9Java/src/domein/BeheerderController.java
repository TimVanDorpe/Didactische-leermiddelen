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
     return   bb.getBeheerderslijst();
    }

}
