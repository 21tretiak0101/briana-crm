package by.ttre16.briana.configuration.root;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * The root context configuration. It scans all configuration beans
 * from {@link by.ttre16.briana.configuration.root } package.
 * These beans will be visible in all servlet contexts.
 *
 * @author Ilia Tretiak
 * @version 1.0
 */

@Configuration
@ComponentScan({
        "by.ttre16.briana.repository",
        "by.ttre16.briana.configuration.root"
})
public class RootContextConfiguration { }
