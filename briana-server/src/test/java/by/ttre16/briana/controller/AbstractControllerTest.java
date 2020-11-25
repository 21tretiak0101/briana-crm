package by.ttre16.briana.controller;

import by.ttre16.briana.configuration.root.RootContextConfiguration;
import by.ttre16.briana.configuration.servlet.ServletContextConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.annotation.PostConstruct;

@ActiveProfiles("development")
//@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        ServletContextConfiguration.class,
        RootContextConfiguration.class
})
@WebAppConfiguration
public abstract class AbstractControllerTest {

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER =
            new CharacterEncodingFilter();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    protected MockMvc mockMvc;

    public static ObjectMapper objectMapper;

    @Autowired
    protected MappingJackson2HttpMessageConverter messageConverter;

    @Autowired
    private WebApplicationContext context;

    @PostConstruct
    private void init() {
        objectMapper = messageConverter.getObjectMapper();
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .build();
    }
}
