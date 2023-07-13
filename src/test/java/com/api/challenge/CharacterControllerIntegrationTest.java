package com.api.challenge;

import com.api.challenge.service.CharacterService;
import com.api.challenge.service.dto.CharacterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CharacterControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterService characterService;

    @Test
    @WithMockUser(username = "${spring.security.user.name}", password = "${spring.security.user.password}")
    void testGetCharacters() throws Exception {
        List<CharacterDTO> characterDTOList = new ArrayList<>();
        CharacterDTO lukeSkywalker = new CharacterDTO("Luke Skywalker", "172", "77", "blond", "fair", "blue",
                "19BBY", "male", "https://swapi.dev/api/planets/1/", null, null, null, null,
                new Date(), new Date(), "https://swapi.dev/api/people/1/");
        characterDTOList.add(lukeSkywalker);

        when(characterService.searchCharacters()).thenReturn(ResponseEntity.ok(characterDTOList));

        mockMvc.perform(MockMvcRequestBuilders.get("/character")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(characterDTOList.size()))
                .andExpect(jsonPath("$[0].name").value(lukeSkywalker.getName()));

        verify(characterService, times(1)).searchCharacters();
    }
}