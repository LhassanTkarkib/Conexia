package com.simplon.GroupeUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/group/user")
public class GroupUserController {

    @Autowired
    private ServiceGroupUser serviceGroupUser;

    @PostMapping
    public ResponseEntity<GroupUserDTO> saveGroupUser(@RequestBody GroupUserDTO groupUserDTO) {
        return new ResponseEntity<>(serviceGroupUser.saveGroupUser(groupUserDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupUserDTO> getGroupUserById(@PathVariable Long id) {
        return new ResponseEntity<>(serviceGroupUser.getGroupUserById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GroupUserDTO>> getAllGroupUsers() {
        return new ResponseEntity<>(serviceGroupUser.getAllGroupUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupUser(@PathVariable Long id) {
        serviceGroupUser.deleteGroupUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<GroupUserDTO> updateGroupUser(@RequestBody GroupUserDTO groupUserDTO) {
        return new ResponseEntity<>(serviceGroupUser.updateGroupUser(groupUserDTO), HttpStatus.OK);
    }
}