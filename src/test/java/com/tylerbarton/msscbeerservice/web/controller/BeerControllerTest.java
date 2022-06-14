package com.tylerbarton.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tylerbarton.msscbeerservice.bootstrap.BeerLoader;
import com.tylerbarton.msscbeerservice.services.BeerService;
import com.tylerbarton.msscbeerservice.web.model.BeerDto;
import com.tylerbarton.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs          // Automatically configures REST docs
class BeerControllerTest {

    @Autowired  // Required to make sure mockMvc isn't null
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    /**
     * Creates a beerDto with NonNull parameters supplied to pass validation checks.
     * @return validation-approved dto
     */
    BeerDto getValidBeerDto(){
        return BeerDto.builder()
                .beerName("testbeer")
                .beerStyle(BeerStyleEnum.IPA)
                .price(new BigDecimal("5.95"))
                .upc(BeerLoader.BEER_1_UPC)
                .build();
    }

    @Test
    void getBeerById() throws Exception {

        given(beerService.getBeerById(any())).willReturn(getValidBeerDto());
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

        given(beerService.saveNewBeer(any())).willReturn(getValidBeerDto());

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))              // Submit test object
                .andExpect(status().isCreated());   // HTTPStatus code check
    }

    @Test
    void updateBeerById() throws Exception {
        given(beerService.updateBeer(any(), any())).willReturn(getValidBeerDto());

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))              // Submit test object
                        .andExpect(status().isCreated());   // HTTPStatus code check
    }
}