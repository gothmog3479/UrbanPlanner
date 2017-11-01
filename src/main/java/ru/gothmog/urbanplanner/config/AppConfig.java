package ru.gothmog.urbanplanner.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import ru.gothmog.urbanplanner.model.dao.registry.auth.EmployeeAuthorityDao;
import ru.gothmog.urbanplanner.model.dao.registry.auth.EmployeeUserDao;
import ru.gothmog.urbanplanner.model.dao.registry.auth.PasswordResetTokenDao;
import ru.gothmog.urbanplanner.model.dao.registry.auth.impl.EmployeeAuthorityDaoImpl;
import ru.gothmog.urbanplanner.model.dao.registry.auth.impl.EmployeeUserDaoImpl;
import ru.gothmog.urbanplanner.model.dao.registry.auth.impl.PasswordResetTokenDaoImpl;
import ru.gothmog.urbanplanner.model.entities.registry.auth.EmployeeAuthority;
import ru.gothmog.urbanplanner.model.entities.registry.auth.EmployeeUser;
import ru.gothmog.urbanplanner.model.entities.registry.auth.PasswordResetToken;

@Configuration
@ComponentScan(value = {"ru.gothmog.urbanplanner.*"})
@PropertySource(value = {"classpath:auth.properties"})
public class AppConfig {
    @Autowired
    private Environment environment;
    @Autowired
    private HibernateConfig hibernateConfig;


    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
        jdbcImpl.setDataSource(hibernateConfig.dataSource());
        jdbcImpl.setUsersByUsernameQuery(environment.getRequiredProperty("usersByQuery"));
        jdbcImpl.setAuthoritiesByUsernameQuery(environment.getRequiredProperty("rolesByQuery"));
        return jdbcImpl;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(hibernateConfig.dataSource());
        return jdbcTemplate;
    }

    @Bean
    public EmployeeUserDao employeeUserDao() {
        return new EmployeeUserDaoImpl(EmployeeUser.class);
    }

    @Bean
    public PasswordResetTokenDao passwordResetTokenDao(){
        return new PasswordResetTokenDaoImpl(PasswordResetToken.class);
    }

    @Bean
    public EmployeeAuthorityDao employeeAuthorityDao(){
        return new EmployeeAuthorityDaoImpl(EmployeeAuthority.class);
    }

}
