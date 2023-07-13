package com.api.challenge.service;

import com.api.challenge.client.rest.StarWarsFeignClient;
import com.api.challenge.config.SwapiProperties;
import com.api.challenge.domain.Character;
import com.api.challenge.repository.CharacterRepository;
import com.api.challenge.service.dto.CharacterDTO;
import com.api.challenge.service.dto.ResponseDTO;
import com.api.challenge.service.mapper.CharacterMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing {@link Character}.
 */
@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class CharacterService {

    private final StarWarsFeignClient starWarsFeignClient;

    private final SwapiProperties swapiProperties;

    private final CharacterRepository characterRepository;

    private final CharacterMapper characterMapper;

    @HystrixCommand(commandKey = "getAllStarWarsCharacters", fallbackMethod = "defaultCharacters")
    public ResponseEntity<List<CharacterDTO>> searchCharacters() {
        ResponseDTO responseDTO = starWarsFeignClient.getStarWarsCharacters();
        try {
            if (responseDTO != null) {
                for (CharacterDTO character : responseDTO.getResults()) {
                    save(character);
                }
                return ResponseEntity.ok(responseDTO.getResults());
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred when trying to access: " + swapiProperties.baseUrl());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Save a character.
     *
     * @param characterDto the entity to save.
     */
    private void save(CharacterDTO characterDto) {
        log.debug("Request to save Character in DB: {}", characterDto);
        Character character = characterMapper.toEntity(characterDto);
        character = characterRepository.save(character);
        characterMapper.toDto(character);
    }

    /**
     * Fallback method
     */
    private ResponseEntity<List<CharacterDTO>> defaultCharacters() {
        return ResponseEntity.ok(List.of());
    }
}