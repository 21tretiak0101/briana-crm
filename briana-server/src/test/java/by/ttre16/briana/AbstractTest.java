package by.ttre16.briana;

import by.ttre16.briana.configuration.root.RootContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("development")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RootContextConfiguration.class})
public abstract class AbstractTest { }
