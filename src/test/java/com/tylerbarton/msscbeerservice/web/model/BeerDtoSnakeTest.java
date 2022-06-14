package com.tylerbarton.msscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Example usage of SNAKE_CASE (ie: created_date) configuration using Jackson for JSON
 * Notice in test/resources/application-snake.properties
 */
@ActiveProfiles("snake")    // Sets the profile
@JsonTest
public class BeerDtoSnakeTest extends BaseTest {

    @Autowired
    ObjectMapper objectMapper;

    /**
     * Demonstrates using the SNAKE_CASE property naming strategy
     * @throws JsonProcessingException
     */
    @Test
    void testSnake() throws JsonProcessingException {
        BeerDto beerDto = getDto();
        String json = objectMapper.writeValueAsString(beerDto);
        System.out.println(json);
    }
}
