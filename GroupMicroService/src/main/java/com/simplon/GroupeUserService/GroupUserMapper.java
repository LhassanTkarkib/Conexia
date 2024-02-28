package com.simplon.GroupeUserService;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GroupUserMapper implements Function<groupUsers, GroupUserDTO>{

    @Override
    public GroupUserDTO apply(groupUsers groupUsers) {
        GroupUserDTO groupUserDTO = new GroupUserDTO();
        groupUserDTO.setIdGroupUser(groupUsers.getIdGroupUser());
        groupUserDTO.setIdGroup(groupUsers.getIdGroup());
        groupUserDTO.setIdUser(groupUsers.getIdUser());
        groupUserDTO.setIdMembers(groupUsers.getIdMembers());
        return groupUserDTO;
    }

    public groupUsers convertToEntity(GroupUserDTO groupUserDTO) {
        groupUsers groupUsers = new groupUsers();
        groupUsers.setIdGroupUser(groupUserDTO.getIdGroupUser());
        groupUsers.setIdGroup(groupUserDTO.getIdGroup());
        groupUsers.setIdUser(groupUserDTO.getIdUser());
        groupUsers.setIdMembers(groupUserDTO.getIdMembers());
        return groupUsers;
    }
}
