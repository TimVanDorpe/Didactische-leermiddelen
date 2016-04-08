package gui;


import domein.ProductenBeheer;
import javafx.scene.layout.HBox;

public class ProductenFrameController extends HBox {
    
    private OverzichtProductenController overzichtPanel;
   // private DetailProductController detailPanelController;

    private ProductenBeheer domeinController;
    
    public ProductenFrameController(ProductenBeheer domeinController) {
        this.domeinController = domeinController;
        overzichtPanel = new OverzichtProductenController(domeinController);
        
//        detailPanelController = new DetailPanelController();
//        domeinController.addObserver(detailPanelController);
        getChildren().add(overzichtPanel);
        
    }

   
    
    
}
