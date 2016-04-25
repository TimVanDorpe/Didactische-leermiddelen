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
         Beheerder beheerder1 = new Beheerder("b@b.be", "root", "beheerder", "0495671235");
        
       bb.registreerBeheerder(beheerder1);
    }
}
