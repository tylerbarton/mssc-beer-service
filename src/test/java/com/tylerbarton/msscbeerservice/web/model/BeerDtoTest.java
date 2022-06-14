package com.tylerbarton.msscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tylerbarton.msscbeerservice.domain.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BeerDto model test
 * Used to demonstrate Jackson library usage for JSON objects
 */
@JsonTest   // Configures environment to test JSON
class BeerDtoTest extends BaseTest{

    @Autowired // Configured by Springboot
    ObjectMapper objectMapper;


    /**
     * Example of serializing a dto object to JSON
     * @throws JsonProcessingException
     */
    @Test
    void testSerializeDto() throws JsonProcessingException {
        BeerDto beerDto = getDto();
        String jsonString = objectMapper.writeValueAsString(beerDto);
        System.out.println(jsonString);
    }

    /**
     * Example of deserializing a json string to a java object
     * @throws JsonProcessingException
     */
    @Test
    void testDeserializeDto() throws JsonProcessingException {
        String json = "{\"id\":\"9ddfd275-3ae0-4f75-8558-cdb63dbf682b\",\"version\":null,\"createdDate\":\"2022-06-14T16:56:35.2599953-05:00\",\"lastModifiedDate\":\"2022-06-14T16:56:35.260996-05:00\",\"beerName\":\"testBeer\",\"beerStyle\":\"ALE\",\"upc\":238492348,\"price\":5.95,\"quantityOnHand\":null}\n";
        BeerDto beerDto = objectMapper.readValue(json, BeerDto.class);
        System.out.println(beerDto.toString());
    }
}