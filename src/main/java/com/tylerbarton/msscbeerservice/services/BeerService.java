package com.tylerbarton.msscbeerservice.services;

import com.tylerbarton.msscbeerservice.web.model.BeerDto;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

/**
 * Acts as an adapter between the service logic and the controller input
 */
public interface BeerService {

    BeerDto getBeerById(UUID beerId) throws ChangeSetPersister.NotFoundException;

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto) throws ChangeSetPersister.NotFoundException;

    BeerDto getBeerByUpc(String upc);
}
