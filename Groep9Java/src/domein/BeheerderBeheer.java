/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.GenericDaoJpa;

/**
 *
 * @author Thomas
 */
public class BeheerderBeheer {

    private ObservableList<Beheerder> beheerderLijst = FXCollections.observableArrayList();
    private GenericDaoJpa gdj;

    public BeheerderBeheer() {
        gdj = new GenericDaoJpa(Product.class);
        BeheerderData data = new BeheerderData(this);
        data.genereerData();
    }

    public void setGdj(GenericDaoJpa gdj) {
        this.gdj = gdj;
    }

    public void registreerBeheerder(Beheerder beheerder) {
        gdj.startTransaction();
        beheerderLijst.add(beheerder);
        gdj.insert(beheerder);
        gdj.commitTransaction();

    }

    public boolean geldigeLogin(Beheerder beheerder) {
        if (!beheerderLijst.stream().anyMatch(b -> b.getEmail().equals(beheerder.getEmail()))) {
            throw new IllegalArgumentException("Gebruiker bestaat niet");
        } else if (!beheerderLijst.stream().anyMatch(b -> b.getWachtwoord().equals(beheerder.getWachtwoord()))) {
            throw new IllegalArgumentException("Wachtwoord is onjuist");
        }

        return true;
    }

    ObservableList<Beheerder> getBeheerderslijst() {
        return beheerderLijst;
    }

    void wijzigReservatie(Beheerder nieuweBeheerder, Beheerder huidigeBeheerder) {
        huidigeBeheerder.setNaam(nieuweBeheerder.getNaam());
        huidigeBeheerder.setEmail(nieuweBeheerder.getEmail());
        huidigeBeheerder.setWachtwoord(nieuweBeheerder.getWachtwoord());

    }

    void removeReservatie(Beheerder huidigeBeheerder) {
        beheerderLijst.remove(huidigeBeheerder);
    }
}
