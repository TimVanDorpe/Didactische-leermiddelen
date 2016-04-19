/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import javafx.collections.ObservableList;

/**
 *
 * @author Jens
 */
public class ReservatieController {
    
    private ReservatieBeheer rb;
    
    public ReservatieController(ReservatieBeheer rb) {
        this.rb = rb;
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
    
    public void removeReservatie(Reservatie r) {
        rb.removeReservatie(r);
    }
    
}
