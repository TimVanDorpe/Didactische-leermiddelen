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