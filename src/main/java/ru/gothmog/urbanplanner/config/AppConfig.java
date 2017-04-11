package ru.gothmog.urbanplanner.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan(value = {"ru.gothmog.urbanplanner.*"})
@PropertySource(value = {"classpath:util.properties"})
public class AppConfig {
    @Autowired
    private Environment environment;
    @Autowired
    private HibernateConfig hibernateConfig;

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(hibernateConfig.dataSource());
        return jdbcTemplate;
    }

}
