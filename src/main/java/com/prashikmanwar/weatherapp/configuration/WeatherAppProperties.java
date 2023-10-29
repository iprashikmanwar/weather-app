package com.prashikmanwar.weatherapp.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value="weather-stack")
public record WeatherAppProperties(String basePath) {
}
