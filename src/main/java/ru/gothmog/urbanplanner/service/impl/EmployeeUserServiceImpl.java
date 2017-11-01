package ru.gothmog.urbanplanner.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gothmog.urbanplanner.model.dao.registry.auth.EmployeeAuthorityDao;
import ru.gothmog.urbanplanner.model.dao.registry.auth.EmployeeUserDao;
import ru.gothmog.urbanplanner.model.dao.registry.auth.PasswordResetTokenDao;
import ru.gothmog.urbanplanner.model.entities.registry.auth.EmployeeAuthority;
import ru.gothmog.urbanplanner.model.entities.registry.auth.EmployeeUser;
import ru.gothmog.urbanplanner.model.entities.registry.auth.PasswordResetToken;
import ru.gothmog.urbanplanner.service.EmployeeUserService;

import java.util.List;
import java.util.Set;

@Service("employeeUserService")
public class EmployeeUserServiceImpl implements EmployeeUserService {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeUserService.class);

    @Autowired
    private EmployeeUserDao employeeUserDao;

    @Autowired
    private EmployeeAuthorityDao employeeAuthorityDao;

    @Autowired
    private PasswordResetTokenDao passwordResetTokenDao;

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordResetTokenDao.findByToken(token);
    }

    @Override
    public void createPasswordResetTokenForUser(EmployeeUser user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenDao.create(myToken);
    }

    @Override
    public EmployeeUser addEmplUser(EmployeeUser employeeUser) {
        return null;
    }

    @Override
    public EmployeeUser findById(long id) {
        return null;
    }

    @Override
    public EmployeeUser findByUsername(String username) {
        return employeeUserDao.findByUsername(username);
    }

    @Override
    public EmployeeUser delete(long id) {
        return null;
    }

    @Override
    public EmployeeUser deleteUsername(String username) {
        return null;
    }

    @Override
    public List<EmployeeUser> findAllEmplUsers() {
        return null;
    }

    @Override
    public EmployeeUser updateEmplUser(EmployeeUser employeeUser) {
        return null;
    }

    @Override
    public EmployeeUser findByEmail(String email) {
        return employeeUserDao.findByEmail(email);
    }

    @Override
    @Transactional
    public EmployeeUser createEmployeeUser(EmployeeUser employeeUser, Set<EmployeeAuthority> employeeAuthorities) {
        EmployeeUser localUser = employeeUserDao.findByUsername(employeeUser.getUsername());
        if (localUser != null){
            LOG.info("user {*} already exists. Nothing will be done", employeeUser.getUsername());
        } else {
            for (EmployeeAuthority employeeAuthority : employeeAuthorities){
                employeeAuthorityDao.create(employeeAuthority);
            }
            employeeUser.getAuthorities().addAll(employeeAuthorities);
            localUser = employeeUserDao.create(employeeUser);
        }
        return localUser;
    }

    @Override
    public EmployeeUser save(EmployeeUser employeeUser) {
        return employeeUserDao.create(employeeUser);
    }
}
