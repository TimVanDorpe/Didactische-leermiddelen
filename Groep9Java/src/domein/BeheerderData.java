/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author Thomas
 */


public class BeheerderData {
        private BeheerderBeheer bb;

    public BeheerderData(BeheerderBeheer bb) {
        this.bb = bb;
    }
        
    public void genereerData(){
         Beheerder beheerder1 = new Beheerder("beheerder@hogent.be", "Wachtwoord1", "beheerder", "0495671235");
         Beheerder beheerder2 = new Beheerder("a", "a", "a", "a");
        
       bb.registreerBeheerder(beheerder1);
       bb.registreerBeheerder(beheerder2);
    }
}
