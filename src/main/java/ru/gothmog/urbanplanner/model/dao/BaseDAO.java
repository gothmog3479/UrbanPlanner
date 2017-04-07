package ru.gothmog.urbanplanner.model.dao;

import org.hibernate.SessionFactory;
import ru.gothmog.urbanplanner.model.entities.IsogdEntity;

/**
 * @author d.grushetskiy
 */
public abstract class BaseDAO<E extends IsogdEntity> {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public abstract void add(E e);
}
