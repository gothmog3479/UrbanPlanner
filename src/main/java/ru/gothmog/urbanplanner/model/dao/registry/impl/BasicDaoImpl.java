package ru.gothmog.urbanplanner.model.dao.registry.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.gothmog.urbanplanner.model.dao.BasicDao;
import ru.gothmog.urbanplanner.model.entities.IsogdEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author d.grushetskiy
 */
@Transactional
public class BasicDaoImpl<T extends IsogdEntity> implements BasicDao<T> {

    private final Class<T> entityClass;

    @Autowired
    protected SessionFactory sessionFactory;

    public BasicDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }


    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T create(T entity) {
        getCurrentSession().save(entity);
        return entity;
    }

    @Override
    public T getById(long id) {
        return getCurrentSession().get(entityClass, id);
    }

    @Override
    public T delete(T entity) {
        getCurrentSession().delete(entity);
        return entity;
    }

    @Override
    public List<T> getList() {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        return sessionFactory.getCurrentSession().createQuery(criteriaQuery).list();
    }

    @Override
    public T update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
        return entity;
    }
}
