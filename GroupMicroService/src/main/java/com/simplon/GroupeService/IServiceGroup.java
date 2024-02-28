package com.simplon.GroupeService;

import java.util.List;

public interface IServiceGroup {
    Group saveGroup(Group group);
    Group getGroupById(Long id);
    List<Group> getAllGroups();
    void deleteGroup(Long id);
    Group updateGroup(Group group);
}