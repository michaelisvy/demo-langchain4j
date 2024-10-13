package com.spring.image;

import org.assertj.core.api.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    private static final Logger logger = LoggerFactory.getLogger(ImageServiceTest.class);

    @Test
    void shouldProcessImage() throws Exception {
        var weatherPrediction = this.imageService.processImage();
        logger.info(weatherPrediction);
        Assertions.assertThat(weatherPrediction).isNotEmpty();
    }
}
