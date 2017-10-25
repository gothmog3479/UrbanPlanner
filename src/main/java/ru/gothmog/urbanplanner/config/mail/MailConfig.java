package ru.gothmog.urbanplanner.config.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource(value = {"classpath:email.properties"})
public class MailConfig {
    /**
     * Java Mail Configuration
     */
    @Value("${spring.mail.username}")
    String mailUsername;
    @Value("${spring.mail.password}")
    String mailPassword;
    @Value("${spring.mail.host}")
    String mailHost;

    @Autowired
    private Environment environment;

    /**
     * <bean id="mailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl">
     */
    @Bean(name = "mailSender")
    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(mailUsername);
        javaMailSender.setPassword(mailPassword);
        javaMailSender.setPort(465);

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.starttls.enable", true);
        javaMailProperties.put("mail.smtp.starttls.required", true);
        javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailProperties.put("mail.smtp.host", mailHost);

        javaMailSender.setJavaMailProperties(javaMailProperties);
        return javaMailSender;
    }
}
