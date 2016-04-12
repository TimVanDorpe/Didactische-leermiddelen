package gui;

import domein.ProductController;
import javafx.scene.layout.HBox;

public class ProductenFrameController extends HBox {
    
    private OverzichtProductenController overzichtPanel;
    private ProductDetailController detailPanelController;

    private ProductController domeinController;
    
    public ProductenFrameController(ProductController domeinController) {
        this.domeinController = domeinController;
        overzichtPanel = new OverzichtProductenController(domeinController);
        
       detailPanelController = new ProductDetailController(domeinController);
        domeinController.addObserver(detailPanelController);
          domeinController.addObserver(overzichtPanel);
        getChildren().addAll(overzichtPanel,detailPanelController);
        
    }

   
    
    
}
