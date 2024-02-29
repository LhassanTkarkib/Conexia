package com.simplon.GroupeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    GroupDTO groupToGroupDTO(Group group);

    Group groupDTOToGroup(GroupDTO groupDTO);
}
