/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Product;
import java.util.List;

/**
 *
 * @author Tim
 */
public class PersistentieController {
    
    private ProductenMapper productenMapper;
    
    public List<Product> geefProducten()
    {
        if (productenMapper == null)
            productenMapper = new ProductenMapper();
        return productenMapper.geefProducten();
    }
    
     public void wijzigProduct(Product product){
         productenMapper.wijzigProduct(product);
     }
}
