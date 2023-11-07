package com.prashikmanwar.weatherapp.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@ConfigurationProperties(prefix="weather-stack.parameters")
public record WeatherAppProperties(String basePath) {
}
