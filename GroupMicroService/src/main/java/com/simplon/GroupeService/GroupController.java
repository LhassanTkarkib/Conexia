package com.simplon.GroupeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private IServiceGroup groupService;

    @GetMapping("/all")
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        log.info("Getting all groups");
        List<Group> groups = groupService.getAllGroups();
        List<GroupDTO> groupDTOs = groups.stream()
                .map(groupMapper::groupToGroupDTO)
                .collect(Collectors.toList());
        log.info("Retrieved " + groupDTOs.size() + " groups");
        return new ResponseEntity<>(groupDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Long id) {
        log.info("Getting group with id " + id);
        Group group = groupService.getGroupById(id);
        log.info("Retrieved group with id " + id);
        return new ResponseEntity<>(groupMapper.groupToGroupDTO(group), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupDTO groupDTO) {
        log.info("Creating group");
        Group group = groupMapper.groupDTOToGroup(groupDTO);
        Group newGroup = groupService.saveGroup(group);
        log.info("Created group with id " + newGroup.getIdGroup());
        return new ResponseEntity<>(groupMapper.groupToGroupDTO(newGroup), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GroupDTO> updateGroup(@PathVariable Long id) {
        log.info("Updating group with id " + id);
        Group group = groupService.getGroupById(id);
        Group newGroup = groupService.updateGroup(group);
        log.info("Updated group with id " + newGroup.getIdGroup());
        return new ResponseEntity<>(groupMapper.groupToGroupDTO(newGroup), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        log.info("Deleting group with id " + id);
        groupService.deleteGroup(id);
        log.info("Deleted group with id " + id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}