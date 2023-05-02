package com.weather.weather.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.annotation.JsonProperty;

@RestController
public class WeatherController {
	
	//This WebService takes info from the public AccuWeather API and gives you back a JSON response with info about weather in Buenos Aires in real time

    private static final String API_KEY = "i4cj8wNfqQE1zAOZwpFDZuVxAo1sO5Gx";
    private static final String LOCATION_KEY = "7894"; // Buenos Aires location key

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/weather")
    public Weather getWeather() {
        String url = String.format("http://dataservice.accuweather.com/currentconditions/v1/%s?apikey=%s&details=true", LOCATION_KEY, API_KEY);
        Weather[] weather = restTemplate.getForObject(url, Weather[].class);
        return weather[0];
    }

    public static class Weather {
        @JsonProperty("WeatherText")
        private String weatherText;

        @JsonProperty("Temperature")
        private Temperature temperature;

        public String getWeatherText() {
            return weatherText;
        }

        public void setWeatherText(String weatherText) {
            this.weatherText = weatherText;
        }

        public double getTemperature() {
            return temperature.getMetric().getValue();
        }

        public void setTemperature(Temperature temperature) {
            this.temperature = temperature;
        }
    }

    private static class Temperature {
        @JsonProperty("Metric")
        private Metric metric;

        public Metric getMetric() {
            return metric;
        }

        public void setMetric(Metric metric) {
            this.metric = metric;
        }
    }

    private static class Metric {
        @JsonProperty("Value")
        private double value;

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }
}



