package com.simplon.friendship;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("FRIEND")
public interface FriendshipClient {
    @GetMapping("/api/v1/friend-ships/requested-accepted-rejected/all")
    ResponseEntity<List<FriendShipDto>> getAllFriendRequestedAcceptedRejected();

    @GetMapping("/api/v1/friend-ships/accepted")
    ResponseEntity<List<FriendShipDto>> getAllFriendAccepted();

    @GetMapping("/api/v1/friend-ships/requested")
    ResponseEntity<List<FriendShipDto>> getAllFriendRequested();

    @GetMapping("/api/v1/friend-ships/rejected")
    ResponseEntity<List<FriendShipDto>> getAllFriendRejected();

    @PostMapping("/api/v1/friend-ships/send-request")
    ResponseEntity<FriendShipDto> addFriendShip(@RequestBody FriendShipDto friendShipDto);

    @PutMapping("/api/v1/friend-ships/update-friend/{friendId}")
    ResponseEntity<FriendShipDto> updateFriendShip();

    @DeleteMapping("/api/v1/friend-ships/delete-friend")
    ResponseEntity<String> deleteFriendByUserIdAndFriendId();

    @GetMapping("/api/v1/friend-ships/search-Specific-friend")
    ResponseEntity<FriendShipDto> searchSpecificFriend();

}
