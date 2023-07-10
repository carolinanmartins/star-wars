package com.api.challenge.rest;

import com.api.challenge.domain.Character;
import com.api.challenge.service.CharacterService;
import com.api.challenge.service.dto.CharacterDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing {@link Character}.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/character", produces = MediaType.APPLICATION_JSON_VALUE)
public class CharacterController {

    private final CharacterService characterService;

    /**
     * {@code GET} : get all star wars characters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of all star wars characters in body.
     */
    @GetMapping
    @Operation(summary = "Get characters",
        description = "Returns all star wars characters",
        security = @SecurityRequirement(name = "Bearer"),
        responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CharacterDTO.class)))), @ApiResponse(responseCode = "404", description = "Characters not found", content = @Content)
    })
//    @PreAuthorize("hasAuthority(\"" + PermissionConstants.API_VIEW + "\")")
    public ResponseEntity<List<CharacterDTO>> getCharacters() {
        return characterService.searchCharacters();
    }
}