package com.tylerbarton.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tylerbarton.msscbeerservice.web.model.BeerDto;
import com.tylerbarton.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class BeerControllerTest {

    @Autowired  // Required to make sure mockMvc isn't null
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * Creates a beerDto with NonNull parameters supplied to pass validation checks.
     * @return validation-approved dto
     */
    BeerDto getValidBeerDto(){
        return BeerDto.builder()
                .beerName("testbeer")
                .beerStyle(BeerStyleEnum.IPA)
                .price(new BigDecimal("5.95"))
                .upc(123891231L)
                .build();
    }

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString())
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    /**
     * Tests the POST method for the BeerController class
     * @throws Exception
     */
    @Test
    void saveNewBeer() throws Exception {
        // Initialize the test object
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))              // Submit test object
                .andExpect(status().isCreated());   // HTTPStatus code check
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))              // Submit test object
                        .andExpect(status().isCreated());   // HTTPStatus code check
    }
}