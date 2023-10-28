package com.prashikmanwar.weatherapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    /***
     * We need a bean of the below method
     * @Component - cannot be used as it is the class level annotation
     * @Bean - is a method level annotation - we can use it here
     */
    @Bean("restTemplateBeanFromRestTemplateConfiguration")
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
