package com.simplon.GroupeUserService;

import java.util.List;

public interface IGroupUserService {
    GroupUserDTO saveGroupUser(GroupUserDTO groupUserDTO);
    GroupUserDTO getGroupUserById(Long id);
    List<GroupUserDTO> getAllGroupUsers();
    void deleteGroupUser(Long id);
    GroupUserDTO updateGroupUser(GroupUserDTO groupUserDTO);
}