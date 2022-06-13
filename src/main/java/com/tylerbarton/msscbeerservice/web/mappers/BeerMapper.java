package com.tylerbarton.msscbeerservice.web.mappers;

import com.tylerbarton.msscbeerservice.domain.Beer;
import com.tylerbarton.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;


/**
 * Converts between Beer dto and entity
 */
@Mapper(uses={DateMapper.class})
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto dto);
}
