package ru.gothmog.urbanplanner.model.dao.registry.auth.impl;

import org.springframework.transaction.annotation.Transactional;
import ru.gothmog.urbanplanner.model.dao.registry.auth.EmployeeAuthorityDao;
import ru.gothmog.urbanplanner.model.dao.registry.impl.BasicDaoImpl;
import ru.gothmog.urbanplanner.model.entities.registry.auth.EmployeeAuthority;

import javax.persistence.Query;

@Transactional
public class EmployeeAuthorityDaoImpl extends BasicDaoImpl<EmployeeAuthority> implements EmployeeAuthorityDao{
    public EmployeeAuthorityDaoImpl(Class<EmployeeAuthority> entityClass) {
        super(entityClass);
    }

    @Override
    public EmployeeAuthority findByName(String authority) {
        Query query = sessionFactory
                .createEntityManager()
                .createQuery("from EmployeeAuthority a where a.authority =: authority");
        query.setParameter("authority", authority);
        return (EmployeeAuthority) query.getSingleResult();
    }
}
