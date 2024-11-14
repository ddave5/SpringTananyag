package hu.masterfield.bankproject.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:hu/masterfield/bankproject/configuration/application-config.xml")
@Import(InfraConfiguration.class)
public class XMLApplicationConfiguration {
    
}
