package com.api.challenge.service;

import com.api.challenge.client.rest.StarWarsFeignClient;
import com.api.challenge.config.SwapiProperties;
import com.api.challenge.domain.Character;
import com.api.challenge.repository.CharacterRepository;
import com.api.challenge.service.dto.CharacterDTO;
import com.api.challenge.service.dto.ResponseDTO;
import com.api.challenge.service.mapper.CharacterMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private StarWarsFeignClient starWarsFeignClient;

    private final SwapiProperties swapiProperties;

    private final CharacterRepository characterRepository;

    private final CharacterMapper characterMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

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
}