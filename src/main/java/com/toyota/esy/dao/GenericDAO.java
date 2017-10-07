package com.toyota.esy.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class GenericDAO<T> {

    private Class<T> className;

    final boolean isDeleted = false;

    @PersistenceContext
    public EntityManager entityManager;

    public EntityManager emCall() {
        if (entityManager == null) {
            EntityManagerFactory emf;
            emf = Persistence.createEntityManagerFactory("Toyota");
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public GenericDAO(Class<T> className) {
        this.className = className;
    }

    public T persist(T entity) {
        entityManager.persist(entity);
        return entity;
    }
    public T merge(T entity) {
        entityManager.merge(entity);
        return entity;
    }
    public T find(int id) {
        T e = entityManager.find(className, id);
        return e;
    }
}
