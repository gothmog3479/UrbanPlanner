package ru.gothmog.urbanplanner.model.dao;

import ru.gothmog.urbanplanner.model.entities.Person;

import java.util.List;

/**
 * @author d.grushetskiy
 */
public interface PersonDAO {

    public void addPerson(Person p);

    public void updatePerson(Person p);

    public List<Person> listPersons();

    public Person getPersonById(int id);

    public void removePerson(int id);
}
