package hu.masterfield.bankproject.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:/hu/masterfield/bankproject/configuration/db.properties")
public class InfraConfiguration {

    @Bean
    public DataSource dataSource(
        @Value("${db.driver}") String driverClassName,
        @Value("${db.url}") String url,
        @Value("${db.username}") String username,
        @Value("${db.password}") String password
    ) {
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);

        return ds;
    }

}
