package by.tms.strore.configuration;

import by.tms.strore.Interceptor.*;
import by.tms.strore.Listener.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpSessionListener;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "by.tms.strore")
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public ServletListenerRegistrationBean<HttpSessionListener> listenerRegistrationBean(){
        return new ServletListenerRegistrationBean<>(new Listener());
    }

    @Autowired
    UserInterceptor userInterceptor;

    @Autowired
    ManagerInterceptor managerInterceptor;

    @Autowired
    AdminInterceptor adminInterceptor;
    @Autowired
    GuestInterceptor guestInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry
                .addInterceptor(guestInterceptor)
                .addPathPatterns("/registration")
                .addPathPatterns("/authorization");


        registry
                .addInterceptor(userInterceptor)
                .addPathPatterns("/out")
                .addPathPatterns("/basket")
                .addPathPatterns("/order");
        registry
                .addInterceptor(managerInterceptor)
                .addPathPatterns("/orders")
                .addPathPatterns("/manufacturers")
                .addPathPatterns("/orders")
                .addPathPatterns("/devices");
        registry
                .addInterceptor(adminInterceptor)
                .addPathPatterns("/allInfo");
    }
}
