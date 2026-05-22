package co.udc.desarrollo.web.calificationsRest.infrastructure.config;

import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.email.SmtpConfig;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class InfraConfig {

    @Value("${smtp.host}")
    private String smtpHost;

    @Value("${smtp.port}")
    private int smtpPort;

    @Value("${smtp.username}")
    private String smtpUser;

    @Value("${smtp.password}")
    private String smtpPassword;

    @Value("${smtp.from.address}")
    private String smtpFromAddress;

    @Value("${smtp.from.name}")
    private String smtpFromName;

    @Bean
    public SmtpConfig smtpConfig() {
        return new SmtpConfig(smtpHost, smtpPort, smtpUser, smtpPassword, smtpFromAddress, smtpFromName);
    }

    @Bean
    public Validator validator() {
        return ValidatorProvider.buildValidator();
    }
}