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
public interface GenericDao<T> {

    public List<T> findAll();  
    public T get(Long id);
    public T update(T object);
    public void delete(T object);
    public void insert(T object);
    public boolean exists(Long id);

} 