package ru.gothmog.urbanplanner.model.dao.registry.auth;

import ru.gothmog.urbanplanner.model.dao.BasicDao;
import ru.gothmog.urbanplanner.model.entities.registry.auth.EmployeeUser;
import ru.gothmog.urbanplanner.model.entities.registry.auth.PasswordResetToken;

import java.util.Date;
import java.util.stream.Stream;

public interface PasswordResetTokenDao extends BasicDao<PasswordResetToken>{

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByEmployeeUser(EmployeeUser employeeUser);

    Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

    void deleteAllExpiredSince(Date now);
}
