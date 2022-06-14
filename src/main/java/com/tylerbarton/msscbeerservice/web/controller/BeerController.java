package com.tylerbarton.msscbeerservice.web.controller;

import com.tylerbarton.msscbeerservice.services.BeerService;
import com.tylerbarton.msscbeerservice.web.model.BeerDto;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    /**
     * For mapping input to the service
     */
    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) throws ChangeSetPersister.NotFoundException {
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Validated @RequestBody BeerDto beerDto){
        return new ResponseEntity(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @Validated @RequestBody BeerDto beerDto) throws ChangeSetPersister.NotFoundException {
        return new ResponseEntity(beerService.updateBeer(beerId, beerDto), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeerById(@PathVariable("beerId") UUID beerId){
        // TODO: impl
    }
}
