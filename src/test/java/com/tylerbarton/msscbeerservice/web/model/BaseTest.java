package com.tylerbarton.msscbeerservice.web.model;

import com.tylerbarton.msscbeerservice.bootstrap.BeerLoader;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Generic testing class
 */
public class BaseTest {
    /**
     * Creates a test beerDto
     * @return fully populated beerDto object
     */
    BeerDto getDto(){
        return BeerDto.builder()
                .beerName("testBeer")
                .beerStyle(BeerStyleEnum.ALE)
                .id(UUID.randomUUID())
                .price(new BigDecimal("5.95"))
                .upc(BeerLoader.BEER_1_UPC)
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .build();
    }
}
