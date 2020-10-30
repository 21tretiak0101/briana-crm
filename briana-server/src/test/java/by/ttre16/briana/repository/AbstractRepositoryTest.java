package by.ttre16.briana.repository;

import by.ttre16.briana.AbstractTest;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRepositoryTest extends AbstractTest {
    @Autowired
    public Flyway flyway;

    @Before
    public void init() {
        flyway.clean();
        flyway.migrate();
    }
}
