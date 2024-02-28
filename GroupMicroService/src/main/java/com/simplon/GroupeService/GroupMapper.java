package com.simplon.GroupeService;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GroupMapper implements Function<Group,GroupDTO >{
    @Override
    public GroupDTO apply(Group group) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setIdGroup(group.getIdGroup());
        groupDTO.setGroupName(group.getGroupName());
        groupDTO.setGroupDescription(group.getGroupDescription());
        groupDTO.setGroupDateCreation(group.getGroupDateCreation());
        groupDTO.setPrivacy(TypePrivacy.PUBLIC);
        return groupDTO;

    }
    public Group toGroup(GroupDTO groupDTO){
        Group group = new Group();
        group.setIdGroup(groupDTO.getIdGroup());
        group.setGroupName(groupDTO.getGroupName());
        group.setGroupDescription(groupDTO.getGroupDescription());
        group.setGroupDateCreation(groupDTO.getGroupDateCreation());
        group.setPrivacy(groupDTO.getPrivacy());
        return group;
    }
}