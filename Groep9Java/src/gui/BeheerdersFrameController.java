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
        bc.deleteObservers();
       detailPanelController = new BeheerdersDetailController(bc);
        bc.addObserver(detailPanelController);
          bc.addObserver(overzichtPanel);
        getChildren().addAll(overzichtPanel,detailPanelController);
        
    }

}
