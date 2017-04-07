package ru.gothmog.urbanplanner.model.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.gothmog.urbanplanner.model.entities.Person;

import java.util.List;

/**
 * @author d.grushetskiy
 */
@Repository
public class PersonDAOImpl implements PersonDAO {

    private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public void addPerson(Person p) {

    }

    @Override
    public void updatePerson(Person p) {

    }

    @Override
    public List<Person> listPersons() {
        return null;
    }

    @Override
    public Person getPersonById(int id) {
        return null;
    }

    @Override
    public void removePerson(int id) {

    }
}
