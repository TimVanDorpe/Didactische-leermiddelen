package gui;

import domein.ProductController;
import domein.ReservatieController;
import javafx.scene.layout.HBox;

public class ReservatiesFrameController extends HBox {
    
    private OverzichtReservatiesController overzichtPanel;
    private ReservatieController rc;
    private ProductController pc;
    
    public ReservatiesFrameController(ReservatieController rc, ProductController pc) {
        this.rc = rc;
        this.pc = pc;
        overzichtPanel = new OverzichtReservatiesController(rc, pc);
        rc.addObserver(overzichtPanel);
        getChildren().addAll(overzichtPanel);
        
    }

   
    
    
}
