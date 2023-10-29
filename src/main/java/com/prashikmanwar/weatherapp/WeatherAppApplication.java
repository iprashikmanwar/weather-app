package com.prashikmanwar.weatherapp;

import com.prashikmanwar.weatherapp.configuration.WeatherAppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(WeatherAppProperties.class)
@SpringBootApplication
public class WeatherAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherAppApplication.class, args);

        /*** To see how context are created and the bean that it store - do getBeanDefinitionNames()
         *
         * ConfigurableApplicationContext context = SpringApplication.run(WeatherAppApplication.class, args);
         * Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
         */
    }

}
