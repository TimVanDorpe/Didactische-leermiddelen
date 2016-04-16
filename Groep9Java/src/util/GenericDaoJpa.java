/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domein.Product;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Timva
 */
public class GenericDaoJpa implements GenericDao<Product> {

    private static final String PU_NAME = "Groep09";
    private static final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory(PU_NAME);
    protected static final EntityManager em
            = emf.createEntityManager();
    private final Class<Product> type;

    public GenericDaoJpa(Class<Product> type) {
        this.type = type;
    }

    public static void closePersistency() {
        em.close();
        emf.close();
    }

    public static void startTransaction() {
        em.getTransaction().begin();
    }

    public static void commitTransaction() {
        em.getTransaction().commit();
    }

    public static void rollbackTransaction() {
        em.getTransaction().rollback();
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery("select entity from "
                + type.getName() + " entity", type).getResultList();

    }

    @Override
    public Product get(Long id) {
        Product entity = em.find(type, id);
        return entity;
    }

    @Override
    public Product update(Product object) {
        return em.merge(object);
    }

    @Override
    public void delete(Product object) {
        em.remove(em.merge(object));

    }

    @Override
    public void insert(Product object) {
        em.persist(object);

    }

    @Override
    public boolean exists(Long id) {
        Product entity = em.find(type, id);
        return entity != null;

    }

}
