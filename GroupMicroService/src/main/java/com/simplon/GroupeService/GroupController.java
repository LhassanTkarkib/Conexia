package com.simplon.GroupeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/group")
public class GroupController {
    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private ServiceGroup groupService;

    @GetMapping("/all")
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        List<GroupDTO> groupDTOs = groups.stream()
                .map(groupMapper::apply)
                .collect(Collectors.toList());
        return new ResponseEntity<>(groupDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Long id) {
        Group group = groupService.getGroupById(id);
        return new ResponseEntity<>(groupMapper.apply(group), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupDTO groupDTO) {
        Group group = groupMapper.toGroup(groupDTO);
        Group newGroup = groupService.saveGroup(group);
        return new ResponseEntity<>(groupMapper.apply(newGroup), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<GroupDTO> updateGroup(@RequestBody GroupDTO groupDTO) {
        Group group = groupMapper.toGroup(groupDTO);
        Group updatedGroup = groupService.updateGroup(group);
        return new ResponseEntity<>(groupMapper.apply(updatedGroup), HttpStatus.OK);
    }

}