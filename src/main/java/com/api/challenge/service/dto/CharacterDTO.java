package com.api.challenge.service.dto;

import com.api.challenge.domain.Character;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link Character} entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDTO implements Serializable {

    private String name;
    private String height;
    private String mass;
    @JsonProperty("hair_color")
    private String hairColor;
    @JsonProperty("skin_color")
    private String skinColor;
    @JsonProperty("eye_color")
    private String eyeColor;
    @JsonProperty("birth_year")
    private String birthYear;
    private String gender;
    @JsonProperty("homeworld")
    private String homeWorld;
    private List<String> films;
    private List<String> species;
    private List<String> vehicles;
    private List<String> starships;
    private Date created;
    private Date edited;
    private String url;
}