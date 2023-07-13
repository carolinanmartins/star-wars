package com.api.challenge.unittest;

import com.api.challenge.rest.CharacterController;
import com.api.challenge.service.CharacterService;
import com.api.challenge.service.dto.CharacterDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class CharacterControllerTest {

    @MockBean
    private CharacterService characterService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        CharacterController characterController = new CharacterController(characterService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(characterController).build();
    }

    @Test
    void testGetCharacters() throws Exception {
        List<CharacterDTO> characters = Arrays.asList(
                new CharacterDTO("Luke Skywalker", "180", "70", "Blond", "Fair", "Blue", "19BBY", "Male", "Tatooine", null, null, null, null, null, null, null),
                new CharacterDTO("Leia Organa", "150", "49", "Brown", "Light", "Brown", "19BBY", "Female", "Alderaan", null, null, null, null, null, null, null)
        );
        when(characterService.searchCharacters()).thenReturn(ResponseEntity.ok(characters));

        mockMvc.perform(get("/character")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Luke Skywalker")))
                .andExpect(jsonPath("$[1].name", is("Leia Organa")));

        verify(characterService, times(1)).searchCharacters();
    }
}