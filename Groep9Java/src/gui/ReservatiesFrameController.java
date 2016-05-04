package gui;

import domein.ProductController;
import domein.ReservatieController;
import javafx.scene.layout.HBox;

public class ReservatiesFrameController extends HBox {
    
    private OverzichtReservatiesController overzichtPanel;
    private ReservatieDetailController detailPanelController;

    private ReservatieController rc;
    private ProductController pc;
    
    public ReservatiesFrameController(ReservatieController rc, ProductController pc) {
        this.rc = rc;
        this.pc = pc;
        overzichtPanel = new OverzichtReservatiesController(rc, pc);
        
       //detailPanelController = new ReservatieDetailController(rc, pc);
        //rc.addObserver(detailPanelController);
       //   rc.addObserver(overzichtPanel);
        getChildren().addAll(overzichtPanel/*,detailPanelController*/);
        
    }

   
    
    
}
