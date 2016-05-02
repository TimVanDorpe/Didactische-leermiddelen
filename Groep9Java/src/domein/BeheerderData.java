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
         Beheerder beheerder1 = new Beheerder("beheerder@hogent.be", "Wachtwoord1", "beheerder", true, "0495671235");
         Beheerder beheerder2 = new Beheerder("a", "a", "a", true, "a");
         Beheerder hoofdbeheerder = new Beheerder("hoofdbeheerder", "hoofdbeheerder", "hoofdbeheerder", true, "hoofdbeheerder");
         
        
       bb.registreerBeheerder(beheerder1);
       bb.registreerBeheerder(beheerder2);
       bb.registreerBeheerder(hoofdbeheerder);
    }
}
