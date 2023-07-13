package com.api.challenge.client.rest;

import com.api.challenge.service.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * The interface star wars' client.
 */
@FeignClient(name = "starWarsClient", url = "${spring.cloud.openfeign.client.config.starWarsClient.url}")
public interface StarWarsFeignClient {

    @GetMapping(value = "/people", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseDTO getStarWarsCharacters();
}