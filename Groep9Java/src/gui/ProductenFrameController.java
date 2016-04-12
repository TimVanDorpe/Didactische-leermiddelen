package gui;


import persistentie.ProductController;
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
        getChildren().addAll(overzichtPanel,detailPanelController);
        
    }

   
    
    
}
