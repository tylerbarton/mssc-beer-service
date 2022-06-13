package com.tylerbarton.msscbeerservice.repositories;

import com.tylerbarton.msscbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * Captures the beer domain to manage. General purpose is to hold information.
 * Uses PagingAndSortingRepository but could use CrudRepository
 */
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
    // Base class adds enough functionality
}
