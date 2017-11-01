package ru.gothmog.urbanplanner.model.dao.registry.auth.impl;

import org.springframework.transaction.annotation.Transactional;
import ru.gothmog.urbanplanner.model.dao.registry.auth.EmployeeUserDao;
import ru.gothmog.urbanplanner.model.dao.registry.impl.BasicDaoImpl;
import ru.gothmog.urbanplanner.model.entities.registry.auth.EmployeeUser;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Transactional
public class EmployeeUserDaoImpl extends BasicDaoImpl<EmployeeUser> implements EmployeeUserDao {

    private String sql_username = "SELECT u FROM users u WHERE u.username LIKE : username";
    private String sql_email = "SELECT u FROM users u WHERE u.email LIKE : email";

    @PersistenceContext
    private EntityManager entityManager;

    public EmployeeUserDaoImpl(Class<EmployeeUser> entityClass) {
        super(entityClass);
    }

    public EmployeeUser findById(long id) {
        EmployeeUser employeeUser = getById(id);
        if (employeeUser != null) {
            initializeCollection(employeeUser.getAuthorities());
        }
        return employeeUser;
    }

    @Override
    public EmployeeUser findByUsername(String username) {
        try {
            EmployeeUser employeeUser = (EmployeeUser) entityManager
                    .createNamedQuery(sql_username)
                    .setParameter("username", username)
                    .getSingleResult();
            if (employeeUser != null) {
                initializeCollection(employeeUser.getAuthorities());
            }
            return employeeUser;
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public EmployeeUser findByEmail(String email) {
        try {
            EmployeeUser employeeUser = (EmployeeUser) entityManager
                    .createNamedQuery(sql_email)
                    .setParameter("email", email)
                    .getSingleResult();
            if (employeeUser != null) {
                initializeCollection(employeeUser.getAuthorities());
            }
            return employeeUser;
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public EmployeeUser deleteByUsername(String username) {
        EmployeeUser employeeUser = (EmployeeUser) entityManager
                .createNamedQuery(sql_username)
                .setParameter("username", username)
                .getSingleResult();
        return delete(employeeUser);
    }

    //An alternative to Hibernate.initialize()
    protected void initializeCollection(Collection<?> collection) {
        if (collection == null) {
            return;
        }
        collection.iterator().hasNext();
    }
}
