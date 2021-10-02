package com.djulb.missc.beerservice.web.mapper;

import com.djulb.missc.beerservice.domain.Beer;
import com.djulb.missc.beerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    Beer beerDtoToBeer (BeerDto dto);
    BeerDto beerToBeerDto (Beer beer);
}
