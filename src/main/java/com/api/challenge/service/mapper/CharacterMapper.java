package com.api.challenge.service.mapper;

import com.api.challenge.domain.Character;
import com.api.challenge.service.dto.CharacterDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Character} and its DTO {@link CharacterDTO}.
 */
@Mapper(componentModel = "spring")
public interface CharacterMapper extends EntityMapper<CharacterDTO, Character> {}