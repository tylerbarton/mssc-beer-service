package com.tylerbarton.msscbeerservice.services;

import com.tylerbarton.msscbeerservice.domain.Beer;
import com.tylerbarton.msscbeerservice.repositories.BeerRepository;
import com.tylerbarton.msscbeerservice.web.mappers.BeerMapper;
import com.tylerbarton.msscbeerservice.web.model.BeerDto;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Logic of the service
 */
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getBeerById(UUID beerId) throws ChangeSetPersister.NotFoundException {
        return beerMapper.beerToBeerDto(
                beerRepository.findById(beerId).orElseThrow(ChangeSetPersister.NotFoundException::new)
        );
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) throws ChangeSetPersister.NotFoundException {
        // Create the beer entity
        Beer beer = beerRepository.findById(beerId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }

    /**
     * Implements the ability to get a beer by upc
     * @param upc
     * @return
     */
    @Cacheable(cacheNames = "beerUpcCache", condition = "#showInventoryOnHand==false")
    @Override
    public BeerDto getBeerByUpc(String upc) {
        return beerMapper.beerToBeerDto(beerRepository.findByUpc(upc));

    }
}
