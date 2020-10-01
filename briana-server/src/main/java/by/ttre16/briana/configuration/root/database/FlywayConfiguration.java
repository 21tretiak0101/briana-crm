package by.ttre16.briana.configuration.root.database;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * Configure Flyway bean for unit testing goals.
 *
 * @author Ilia Tretiak
 * @version 1.0
 */

@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:conf/flyway.development.conf")
public class FlywayConfiguration {

    private final DataSource dataSource;

    private final Environment environment;

    @Bean
    public Flyway flyway() {
        ClassicConfiguration configuration = new ClassicConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setLocationsAsStrings(
                environment.getProperty("flyway.locations")
        );
        return new Flyway(configuration);
    }
}
