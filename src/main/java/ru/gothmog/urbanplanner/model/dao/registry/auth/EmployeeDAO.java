package ru.gothmog.urbanplanner.model.dao.registry.auth;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.gothmog.urbanplanner.model.entities.registry.auth.Employee;

/**
 * @author d.grushetskiy
 */
@Repository
public class EmployeeDAO  {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public void add(Employee employee) {

    }
}
