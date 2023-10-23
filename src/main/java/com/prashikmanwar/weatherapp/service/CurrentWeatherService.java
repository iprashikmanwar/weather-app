package com.prashikmanwar.weatherapp.service;

import com.prashikmanwar.weatherapp.model.CurrentWeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class CurrentWeatherService {
    private final Logger logger = LoggerFactory.getLogger(CurrentWeatherService.class);

    @Autowired
    private RestTemplate restClient;

    private final String WeatherStack_URL = "http://api.weatherstack.com/current?access_key=27c50212cc3694b06738080e84844a3f&query=Pune";

    CurrentWeatherService(RestTemplate restClient){
        this.restClient = restClient;
    }

    public CurrentWeatherResponse requestCurrentWeather(){
        try{
            URI uri = new URI(WeatherStack_URL);
            ResponseEntity<CurrentWeatherResponse> wsResponse = restClient.getForEntity(uri, CurrentWeatherResponse.class);
            return new CurrentWeatherResponse();//"ARequest","Location","Currently");
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
