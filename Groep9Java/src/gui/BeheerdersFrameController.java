/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.BeheerderController;
import domein.ProductController;
import domein.ReservatieController;
import javafx.scene.layout.HBox;

/**
 *
 * @author Jarne
 */
public class BeheerdersFrameController extends HBox {
    private OverzichtBeheerdersController overzichtPanel;
    private BeheerdersDetailController detailPanelController;

    private BeheerderController bc;

    public BeheerdersFrameController(BeheerderController bc) {
        this.bc = bc;
        
        overzichtPanel = new OverzichtBeheerdersController(bc);
        
       detailPanelController = new BeheerdersDetailController(bc);
        bc.addObserver(detailPanelController);
          bc.addObserver(overzichtPanel);
        getChildren().addAll(overzichtPanel,detailPanelController);
        
    }

}
