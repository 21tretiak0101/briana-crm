package by.ttre16.briana.configuration.root.database;

import by.ttre16.briana.annotation.ProfileProperties;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Properties;


/**
 * Production specific configuration - creates a postgresql datasource.
 *
 * Set -Dspring.profiles.active=production to build project with this config.
 *
 * @author Ilia Tretiak
 * @version 1.0
 * @see DevelopmentDatabaseConfiguration
 */

@Configuration
@Profile("production")
@PropertySource("classpath:database/production/postgresql.properties")
@RequiredArgsConstructor
public class ProductionDatabaseConfiguration {

    private final Environment environment;

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(
                environment.getProperty("postgresql.driver")
        );
        dataSource.setUrl(
                environment.getProperty("postgresql.url")
        );
        dataSource.setUsername(
                environment.getProperty("postgresql.username")
        );
        dataSource.setPassword(
                environment.getProperty("postgresql.password")
        );
        return dataSource;
    }

    @Bean
    @ProfileProperties
    public Properties additionalProductionProperties() {
        Properties productionProperties = new Properties();
        productionProperties.setProperty(
                "database.platform",
                environment.getProperty("database.platform")
        );
        return productionProperties;
    }
}
