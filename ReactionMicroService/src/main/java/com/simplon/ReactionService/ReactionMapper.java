package com.simplon.ReactionService;

import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ReactionMapper {
    ReactionDTO toDTO(Reaction reaction);
    Reaction toEntity(ReactionDTO reactionDTO);
}

