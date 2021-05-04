package by.tms.strore.configuration;

import by.tms.strore.Listener.Listener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpSessionListener;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "by.tms.strore")
public class WebConfig {
    @Bean
    public ServletListenerRegistrationBean<HttpSessionListener> listenerRegistrationBean(){
        return new ServletListenerRegistrationBean<>(new Listener());
    }
}
