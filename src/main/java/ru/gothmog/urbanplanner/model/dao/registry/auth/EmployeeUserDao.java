package ru.gothmog.urbanplanner.model.dao.registry.auth;

import ru.gothmog.urbanplanner.model.dao.BasicDao;
import ru.gothmog.urbanplanner.model.entities.registry.auth.EmployeeUser;

public interface EmployeeUserDao extends BasicDao<EmployeeUser> {
    EmployeeUser findByUsername(String username);

    EmployeeUser findByEmail(String email);

    EmployeeUser deleteByUsername(String username);
}
