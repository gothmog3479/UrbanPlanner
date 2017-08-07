package ru.gothmog.urbanplanner.db.dao;

import ru.gothmog.urbanplanner.model.entities.registry.auth.EmployeeUser;

import java.util.List;

/**
 * @author d.grushetskiy
 */
public interface UserDao {

    void createUser(EmployeeUser user);

    void updateUser(EmployeeUser user);

    void deleteUser(long id);

    List<EmployeeUser> getAllUsers();

    EmployeeUser getUserById(long id);
}
