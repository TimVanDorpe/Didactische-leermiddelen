/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.List;

/**
 *
 * @author Timva
 */
public interface GenericDao<product> {

    public List<product> findAll();  
    public product get(Long id);
    public product update(product object);
    public void delete(product object);
    public void insert(product object);
    public boolean exists(Long id);

} 