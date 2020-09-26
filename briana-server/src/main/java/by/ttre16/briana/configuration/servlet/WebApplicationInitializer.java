package by.ttre16.briana.configuration.servlet;

import by.ttre16.briana.configuration.root.RootContextConfiguration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.servlet.support
        .AbstractAnnotationConfigDispatcherServletInitializer;

public class WebApplicationInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        super.onStartup(context);
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootContextConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ServletContextConfiguration.class};
    }
}
