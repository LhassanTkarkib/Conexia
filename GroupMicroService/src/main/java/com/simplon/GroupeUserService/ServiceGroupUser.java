package com.simplon.GroupeUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceGroupUser implements IGroupUser {

    @Autowired
    private GroupUserRepository groupUserRepository;

    @Autowired
    private GroupUserMapper groupUserMapper;


    public GroupUserDTO saveGroupUser(GroupUserDTO groupUserDTO) {
        groupUsers groupUsers = groupUserMapper.convertToEntity(groupUserDTO);
        return groupUserMapper.apply(groupUserRepository.save(groupUsers));
    }

    public GroupUserDTO getGroupUserById(Long id) {
        return groupUserMapper.apply(groupUserRepository.findById(id).get());
    }

    public List<GroupUserDTO> getAllGroupUsers() {
        return groupUserRepository.findAll().stream().map(groupUserMapper).collect(Collectors.toList());
    }

    public void deleteGroupUser(Long id) {
        groupUserRepository.deleteById(id);
    }

    public GroupUserDTO updateGroupUser(GroupUserDTO groupUserDTO) {
        groupUsers groupUsers = groupUserMapper.convertToEntity(groupUserDTO);
        return groupUserMapper.apply(groupUserRepository.save(groupUsers));
    }


}
