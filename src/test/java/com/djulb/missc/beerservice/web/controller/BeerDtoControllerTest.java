package com.djulb.missc.beerservice.web.controller;

import com.djulb.missc.beerservice.web.model.BeerDto;
import com.djulb.missc.beerservice.web.model.BeerStyleEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(BeerDtoController.class)
class BeerDtoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(
                get("/api/v1/beer/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto dto = getValidBeer();
        String beerDtoJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(
                post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    private BeerDto getValidBeer() {
        BeerDto dto = BeerDto.builder().build();
        dto.setBeerStyle(BeerStyleEnum.ALE);
        dto.setBeerName("Some beer");
        dto.setPrice(new BigDecimal(20.568));
        dto.setUpc(12345L);
        dto.setQuantityOnHand(20);
        return dto;
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto beerDto = getValidBeer();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }
}