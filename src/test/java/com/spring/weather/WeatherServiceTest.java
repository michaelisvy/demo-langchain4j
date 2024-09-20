package com.spring.weather;

import org.assertj.core.api.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceTest.class);

    @Test
    void shouldAnalyseWeather() throws IOException {
        var weatherPrediction = this.weatherService.analyseWeather();
        logger.info(weatherPrediction);
        Assertions.assertThat(weatherPrediction).isNotEmpty();
    }
}
