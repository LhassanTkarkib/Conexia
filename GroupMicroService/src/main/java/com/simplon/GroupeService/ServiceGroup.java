package com.simplon.GroupeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceGroup implements IServiceGroup{

    @Autowired
    private GroupRepository groupRepository;

    public Group saveGroup(Group group) {
        try {
            return groupRepository.save(group);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save group");
        }
    }

    public Group getGroupById(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        if (!group.isPresent()) {
            throw new RuntimeException("Group id not found : " + id);
        }
        return group.get();
    }

    public List<Group> getAllGroups() {
        try {
            return groupRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve groups");
        }
    }

    public Group updateGroup(Group group) {
        try {
            return groupRepository.save(group);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update group");
        }
    }

    public void deleteGroup(Long id) {
        try {
            groupRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete group with id : " + id);
        }
    }

}