package com.api.challenge.unittest;

import com.api.challenge.domain.Character;
import com.api.challenge.repository.CharacterRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CharacterRepositoryTest {

    @Autowired
    private CharacterRepository characterRepository;

    private static List<Character> characterList;

    @BeforeEach
    public void setup() {
        characterList = new ArrayList<>() {{
            add(createCharacter(1));
            add(createCharacter(2));
        }};
        characterRepository.saveAll(characterList);
    }

    @AfterEach
    public void destroy() {
        characterRepository.deleteAll();
    }

    @Test
    @DirtiesContext
    void testFindAll() {
        List<Character> characters = characterRepository.findAll();
        assertThat(characters).containsExactlyElementsOf(characterList);
    }

    @Test
    @DirtiesContext
    void testFindById() {
        Character expectedMessage = characterList.get(0);

        Optional<Character> actualMessage = characterRepository.findById(1L);
        assertEquals(Optional.of(expectedMessage), actualMessage);
    }

    @Test
    @DirtiesContext
    void testSave() {
        Character expectedCharacter = createCharacter(3);
        Character returnedCharacter = characterRepository.save(expectedCharacter);

        assertEquals(expectedCharacter, returnedCharacter);
    }

    @Test
    @DirtiesContext
    void testUpdate() {
        Character character = createCharacter(4);

        characterRepository.save(character);

        character.setBirthYear("11111");
        characterRepository.save(character);

        Optional<Character> optionalDocument = characterRepository.findById(character.getId());

        assertThat(optionalDocument).isPresent();
        Character updatedCharacter = optionalDocument.get();
        assertThat(updatedCharacter.getBirthYear()).isEqualTo("11111");
    }

    @Test
    @DirtiesContext
    void testDelete() {
        Character documentResult = createCharacter(5);
        characterRepository.save(documentResult);

        characterRepository.delete(documentResult);
        Optional<Character> deletedCharacter = characterRepository.findById(documentResult.getId());
        assertFalse(deletedCharacter.isPresent());
    }

    private Character createCharacter(long id) {
        return new Character(id, "name", "height", "mass", "hairColor", "skinColor", "eyeColor", "birthYear",
                "gender", "homeWorld", null, null, null, null, new Date(), new Date(), "url");
    }
}