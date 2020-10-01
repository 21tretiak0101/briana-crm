package by.ttre16.briana.configuration.root.database;

import by.ttre16.briana.annotation.ProfileProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.lang.Boolean.parseBoolean;
import static org.hibernate.cfg.AvailableSettings.*;

/**
 * Configure Hibernate (entity manager, transactionManager)
 * as the JPA provider.
 *
 * @author Ilia Tretiak
 * @version 1.0
 */

@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:jpa/hibernate.properties")
@EnableTransactionManagement
public class JpaConfiguration {

    private final Environment environment;

    private final DataSource dataSource;

    @ProfileProperties
    private final Properties properties;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("by.ttre16.briana.entity");
        entityManagerFactoryBean.setJpaPropertyMap(jpaPropertyMap());
        return entityManagerFactoryBean;
    }

    @Bean
    public Map<String, String> jpaPropertyMap() {
        Map<String, String> jpaPropertyMap = new HashMap<>();
        jpaPropertyMap.put(
                FORMAT_SQL,
                environment.getProperty("hibernate.format_sql")
        );
        jpaPropertyMap.put(
                USE_SQL_COMMENTS,
                environment.getProperty("hibernate.use_sql_comments")
        );
        jpaPropertyMap.put(
                HBM2DDL_AUTO,
                environment.getProperty("hibernate.hbm2ddl.auto")
        );
        return jpaPropertyMap;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        transactionManager.setEntityManagerFactory(
                entityManagerFactory().getObject()
        );
        return transactionManager;
    }

    @Bean
    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter =
                new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(
                parseBoolean(environment.getProperty("jpa.show_sql"))
        );
        jpaVendorAdapter.setDatabasePlatform(
                properties.getProperty("database.platform")
        );
        return jpaVendorAdapter;
    }
}
