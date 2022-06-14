package com.tylerbarton.msscbeerservice.bootstrap;

import com.tylerbarton.msscbeerservice.domain.Beer;
import com.tylerbarton.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Creates beer objects via bootstrapping
 * Implements CommandLineRunner to call on start up.
 */
@Component // Required so Spring context picks it up
public class BeerLoader implements CommandLineRunner {

    // Standard UPCs
    public static final String BEER_1_UPC = "0771243100036";
    public static final String BEER_2_UPC = "0771243400019";
    public static final String BEER_3_UPC = "0087784475214";

    private final BeerRepository beerRepository;

    /**
     * Initialize beerloader with a repository
     * @param beerRepository Repository responsible for holding beer data
     */
    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    /**
     * Runs the bean within a SpringApplication
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    /**
     * Loads the beer objects from the repository
     */
    private void loadBeerObjects(){
        if(beerRepository.count() == 0){
            // If no beers contained, create
            beerRepository.save(Beer.builder()
                            .beerName("original beer")
                            .beerStyle("IPA")
                            .quantityToBrew(100)
                            .minOnHand(12)
                            .upc(BEER_1_UPC)
                            .price(new BigDecimal("5.95"))
                        .build());

            beerRepository.save(Beer.builder()
                    .beerName("new aged beer")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("6.95"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("third aged beer")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal("6.95"))
                    .build());
        }

        // Log the loaded beers
        System.out.println("Loaded Beers:" + beerRepository.count());
    }

}
