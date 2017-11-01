package ru.gothmog.urbanplanner.model.dao.registry.auth;

import ru.gothmog.urbanplanner.model.dao.BasicDao;
import ru.gothmog.urbanplanner.model.entities.registry.auth.EmployeeAuthority;

public interface EmployeeAuthorityDao extends BasicDao<EmployeeAuthority>{

    EmployeeAuthority findByName(String name);
}
