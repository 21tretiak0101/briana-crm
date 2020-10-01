package by.ttre16.briana.configuration.root.database;

import by.ttre16.briana.annotation.ProfileProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Properties;

/**
 * Testing specific configuration - creates an in-memory hsqldb datasource
 * and inserts some test data on the database.
 *
 * @author Ilia Tretiak
 * @version 1.0
 * @see ProductionDatabaseConfiguration
 */

@Configuration
@Profile("development")
@PropertySource("classpath:database/development/hsqldb.properties")
@RequiredArgsConstructor
public class DevelopmentDatabaseConfiguration {

    private final Environment environment;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                environment.getRequiredProperty("hsqldb.driver")
        );
        dataSource.setUrl(
                environment.getProperty("hsqldb.url")
        );
        dataSource.setUsername(
                environment.getProperty("hsqldb.username")
        );
        dataSource.setPassword(
                environment.getProperty("hsqldb.password")
        );
        return dataSource;
    }

    @Bean
    @ProfileProperties
    public Properties additionalDevelopmentProperties() {
        Properties developmentProperties = new Properties();
        developmentProperties.setProperty(
                "database.platform",
                environment.getProperty("database.platform")
        );
        return developmentProperties;
    }
}
