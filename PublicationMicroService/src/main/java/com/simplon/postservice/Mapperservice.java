package com.simplon.postservice;

import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface Mapperservice {
    PostDTO toDTO(Post post);
    Post toEntity(PostDTO postDTO);
}
