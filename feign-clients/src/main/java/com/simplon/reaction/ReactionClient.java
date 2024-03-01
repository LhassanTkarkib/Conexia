package com.simplon.reaction;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@FeignClient(name = "REACTION")
public interface ReactionClient {
    @GetMapping("/{postId}")
     ResponseEntity<Page<ReactionDTO>> getAllReactionsByPostId(@PathVariable long postId , @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size);

    @GetMapping("/user/{userId}")
     ResponseEntity<Page<ReactionDTO>> getAllReactionsByUserId(@PathVariable long userId, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size);
    @PostMapping
     ResponseEntity<ReactionDTO> addingReactionToAPost(
            @RequestBody ReactionDTO reactionDTO
    );
    @DeleteMapping("/{id}")
     ResponseEntity<String> deleteAReaction(@PathVariable Long id);
}
