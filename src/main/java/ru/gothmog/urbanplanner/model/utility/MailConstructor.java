package ru.gothmog.urbanplanner.model.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import ru.gothmog.urbanplanner.model.entities.registry.auth.EmployeeUser;

import java.util.Locale;

@Component
@PropertySource(value = {"classpath:email.properties"})
public class MailConstructor {
    @Autowired
    private Environment environment;

    public SimpleMailMessage constructResetTokenEmail(String contextPath, Locale locale, String token, EmployeeUser employeeUser, String password) {
        String url = contextPath + "/newUser?token=" + token;
        String message = "\nНажмите эту ссылку, чтобы подтвердить свою электронную почту и изменить свою личную информацию. Ваш пароль: \n" + password;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(employeeUser.getEmail());
        email.setSubject("Urban Planner - Новый Сотрудник");
        email.setText(url + message);
        email.setFrom(environment.getProperty("support.email"));
        return email;
    }
}
