package by.ttre16.briana.configuration.servlet;

import by.ttre16.briana.configuration.root.RootContextConfiguration;
import by.ttre16.briana.configuration.root.security.SecurityConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * The replacement for most of the content of web.xml.
 * It sets up the root and the servlet context configurations.
 *
 * @author Ilia Tretiak
 * @version 1.0
 */

public class WebApplicationInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        super.onStartup(context);
        context.setInitParameter(
                "spring.profiles.default",
                "development"
        );
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                RootContextConfiguration.class,
                SecurityConfiguration.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ServletContextConfiguration.class};
    }
}
