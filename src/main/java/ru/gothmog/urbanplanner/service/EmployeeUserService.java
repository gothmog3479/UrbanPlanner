package ru.gothmog.urbanplanner.service;

import ru.gothmog.urbanplanner.model.entities.registry.auth.EmployeeAuthority;
import ru.gothmog.urbanplanner.model.entities.registry.auth.EmployeeUser;
import ru.gothmog.urbanplanner.model.entities.registry.auth.PasswordResetToken;

import java.util.List;
import java.util.Set;

public interface EmployeeUserService {
    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordResetTokenForUser(final EmployeeUser user, final String token);

    EmployeeUser addEmplUser(EmployeeUser employeeUser);

    EmployeeUser findById(long id);

    EmployeeUser findByUsername(String username);

    EmployeeUser delete(long id);

    EmployeeUser deleteUsername(String username);

    List<EmployeeUser> findAllEmplUsers();

    EmployeeUser updateEmplUser(EmployeeUser employeeUser);

    EmployeeUser findByEmail(String email);

    EmployeeUser createEmployeeUser(EmployeeUser employeeUser, Set<EmployeeAuthority> employeeAuthorities);

    EmployeeUser save(EmployeeUser employeeUser);
}
