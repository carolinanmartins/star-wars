package com.api.challenge.unittest;

import com.api.challenge.client.rest.StarWarsFeignClient;
import com.api.challenge.config.SwapiProperties;
import com.api.challenge.domain.Character;
import com.api.challenge.repository.CharacterRepository;
import com.api.challenge.service.CharacterService;
import com.api.challenge.service.dto.CharacterDTO;
import com.api.challenge.service.dto.ResponseDTO;
import com.api.challenge.service.mapper.CharacterMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CharacterServiceTest {

    @InjectMocks
    private CharacterService characterService;

    @Mock
    private CharacterRepository characterRepository;

    @Mock
    private CharacterMapper characterMapper;

    @Mock
    private StarWarsFeignClient starWarsFeignClient;

    @Mock
    private SwapiProperties swapiProperties;

    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpResponse<String> httpResponse;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSearchCharacters() throws Exception {
        CharacterDTO characterDTO = createCharacterDTO(1);
        when(characterMapper.toDto(any(Character.class))).thenReturn(characterDTO);

        Character mappedCharacter = createCharacter(1);
        when(characterMapper.toEntity(any(CharacterDTO.class))).thenReturn(mappedCharacter);

        Character savedCharacter = createCharacter(1);
        when(characterRepository.save(any(Character.class))).thenReturn(savedCharacter);

        when(swapiProperties.baseUrl()).thenReturn("https://swapi.dev/api/");

        when(starWarsFeignClient.getStarWarsCharacters()).thenReturn(new ResponseDTO(0, null, null, List.of(characterDTO)));

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn(getMockResponseJson());

        ResponseEntity<List<CharacterDTO>> responseEntity = characterService.searchCharacters();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<CharacterDTO> characterDTOs = responseEntity.getBody();
        assert characterDTOs != null;
        assertEquals(1, characterDTOs.size());
        assertEquals(characterDTO, characterDTOs.get(0));
        assertEquals(characterDTOs.get(0).getName(), savedCharacter.getName());
        assertEquals(characterDTOs.get(0).getHeight(), savedCharacter.getHeight());
        assertEquals(characterDTOs.get(0).getMass(), savedCharacter.getMass());

        verify(characterRepository, times(1)).save(any(Character.class));
    }

    private String getMockResponseJson() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<CharacterDTO> characterDTOs = new ArrayList<>();
        characterDTOs.add(createCharacterDTO(1));
        responseDTO.setResults(characterDTOs);
        try {
            return objectMapper.writeValueAsString(responseDTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Character createCharacter(long id) {
        return new Character(id, "name", "height", "mass", "hairColor", "skinColor", "eyeColor", "birthYear",
                "gender", "homeWorld", null, null, null, null, new Date(), new Date(), "url");
    }

    private CharacterDTO createCharacterDTO(long id) {
        return new CharacterDTO("name", "height", "mass", "hairColor", "skinColor", "eyeColor", "birthYear",
                "gender", "homeWorld", null, null, null, null, new Date(), new Date(), "url");
    }
}