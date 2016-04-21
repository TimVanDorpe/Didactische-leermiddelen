package gui;

import domein.ProductController;
import domein.ReservatieController;
import javafx.scene.layout.HBox;

public class ReservatiesFrameController extends HBox {
    
    private OverzichtReservatiesController overzichtPanel;
    private ReservatieDetailController detailPanelController;

    private ReservatieController rc;
    
    public ReservatiesFrameController(ReservatieController rc) {
        this.rc = rc;
        overzichtPanel = new OverzichtReservatiesController(rc);
        
       detailPanelController = new ReservatieDetailController(rc);
        rc.addObserver(detailPanelController);
          rc.addObserver(overzichtPanel);
        getChildren().addAll(overzichtPanel,detailPanelController);
        
    }

   
    
    
}
