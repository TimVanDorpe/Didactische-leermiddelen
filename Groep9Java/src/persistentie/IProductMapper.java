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
 * @author Jens
 */
public interface IProductMapper {
    
    public List<Product> geefProducten();

    public void wijzigProduct(Product product);
    
    
}
