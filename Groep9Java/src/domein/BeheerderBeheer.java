package domein;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import util.GenericDaoJpa;

public class BeheerderBeheer {

    private ObservableList<Beheerder> beheerderLijst = FXCollections.observableArrayList();

    private GenericDaoJpa gdj;
    private SortedList<Beheerder> sortedList;
    private final Comparator<Beheerder> byBeheerderNaam = (r1, r2) -> r1.getNaam().compareToIgnoreCase(r2.getNaam());
    private final Comparator<Beheerder> sortOrder = byBeheerderNaam;

    public BeheerderBeheer() {
        gdj = new GenericDaoJpa(Beheerder.class);
        BeheerderData data = new BeheerderData(this);
        sortedList = beheerderLijst.sorted(sortOrder);
        data.genereerData();
    }

    public SortedList<Beheerder> getSortedList() {
        sortedList = beheerderLijst.sorted(sortOrder);
        return sortedList;
    }

    public void setGdj(GenericDaoJpa gdj) {
        this.gdj = gdj;
    }

    public void setBeheerderLijst(ObservableList<Beheerder> beheerderLijst) {
        this.beheerderLijst = beheerderLijst;
    }

    public void registreerBeheerder(Beheerder beheerder) {
        gdj.startTransaction();
        beheerderLijst.add(beheerder);
        gdj.insert(beheerder);
        gdj.commitTransaction();

    }

    public boolean geldigeLogin(Beheerder beheerder) {
        if (!beheerderLijst.stream().anyMatch(b -> b.getEmail().equals(beheerder.getEmail()))) {
            throw new IllegalArgumentException("Gebruikersnaam of wachtwoord is onjuist");
        } else if (!beheerderLijst.stream().anyMatch(b -> b.getWachtwoord().equals(beheerder.getWachtwoord()))) {
            throw new IllegalArgumentException("Gebruikersnaam of wachtwoord is onjuist");
        }

        return true;
    }

    ObservableList<Beheerder> getBeheerderslijst() {
        return sortedList;
    }

    void wijzigBeheerder(Beheerder nieuweBeheerder, Beheerder huidigeBeheerder) {
        huidigeBeheerder.setNaam(nieuweBeheerder.getNaam());
        huidigeBeheerder.setEmail(nieuweBeheerder.getEmail());
        huidigeBeheerder.setWachtwoord(nieuweBeheerder.getWachtwoord());

    }

    void voegBeheerderToe(String naam, String email, String wachtwoord) {
        Beheerder beh = new Beheerder(naam, email, wachtwoord, false);
        gdj.startTransaction();
        beheerderLijst.add(beh);
        gdj.insert(beh);
        gdj.commitTransaction();
    }

    public boolean isEmailUniek(String email, String naamGeselecteerdeBeheerder) {

        if (beheerderLijst.stream().anyMatch(p -> p.getEmail().toLowerCase().equals(email.toLowerCase()))) {
            return false;
        }
        return true;
    }

    public ObservableList<Beheerder> zoekOpBeheerdersNaam(String beheerderNaam) {

        ObservableList<Beheerder> beheerderLijstMetTrefwoord = FXCollections.observableArrayList();
        List<Beheerder> rr = new ArrayList<>();

        for (Beheerder r : beheerderLijst) {
            if (r.getNaam().toLowerCase().contains(beheerderNaam.toLowerCase())) {
                rr.add(r);
            }
        }
        beheerderLijstMetTrefwoord = FXCollections.observableArrayList(rr);
        sortedList = beheerderLijstMetTrefwoord.sorted(sortOrder);

        return sortedList;

    }

    void removeBeheerder(Beheerder huidigeBeheerder) {
        gdj.startTransaction();
        beheerderLijst.remove(huidigeBeheerder);
        gdj.delete(huidigeBeheerder);
        gdj.commitTransaction();
    }
}
