package hu.masterfield.bankproject.configuration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:hu/masterfield/bankproject/configuration/application-config.xml")
@Import(InfraConfiguration.class)
@SpringBootApplication
@EnableCaching
@EnableAspectJAutoProxy
@ComponentScan("hu.masterfield.bankproject.aspect")
public class XMLApplicationConfiguration {
    
}
