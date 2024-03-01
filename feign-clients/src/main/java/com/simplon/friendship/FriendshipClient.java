package com.simplon.friendship;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("FRIEND")
public interface FriendshipClient {
    @GetMapping("/api/v1/friend-ships/accepted")
    ResponseEntity<List<FriendShipDto>> getAllFriendAccepted();
}
