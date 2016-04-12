package gui;

import domein.DomeinController;
import javafx.scene.layout.HBox;

public class ProductenFrameController extends HBox {
    
    private OverzichtProductenController overzichtPanel;
    private ProductDetailController detailPanelController;

    private DomeinController domeinController;
    
    public ProductenFrameController(DomeinController domeinController) {
        this.domeinController = domeinController;
        overzichtPanel = new OverzichtProductenController(domeinController);
        
       detailPanelController = new ProductDetailController(domeinController);
        domeinController.addObserver(detailPanelController);
          domeinController.addObserver(overzichtPanel);
        getChildren().addAll(overzichtPanel,detailPanelController);
        
    }

   
    
    
}
