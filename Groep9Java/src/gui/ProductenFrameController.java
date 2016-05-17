package gui;

import domein.ProductController;
import javafx.scene.layout.HBox;

public class ProductenFrameController extends HBox {

    private OverzichtProductenController overzichtPanel;
    private ProductDetailController detailPanelController;

    private ProductController pc;

    public ProductenFrameController(ProductController productController) {
        this.pc = productController;
        overzichtPanel = new OverzichtProductenController(productController);
        detailPanelController = new ProductDetailController(productController);
        productController.deleteObservers();
        productController.addObserver(detailPanelController);

        //overzichtpanneel is geen observer meer anders past die de lijst van producten niet automatisch aan!
        //   domeinController.addObserver(overzichtPanel);
        getChildren().addAll(overzichtPanel, detailPanelController);

    }

}
