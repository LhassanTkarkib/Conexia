package com.simplon.ReactionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reaction")
public class ReactionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReactionController.class);
    private final ReactionService reactionService;

    @Autowired
    public ReactionController(ReactionService reactionService){
        this.reactionService=reactionService;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<ReactionDTO>> getAllReactionsByPostId(@PathVariable long postId){
        List<ReactionDTO> reactionDTOS=reactionService.getAllReactionsByPostId(postId);
        LOGGER.info("reactions fetched successfuly ");
        return new ResponseEntity<>(reactionDTOS, HttpStatus.OK);

    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<ReactionDTO>> getAllReactionsByUserId(@PathVariable long userId){
        List<ReactionDTO> reactionDTOS=reactionService.getAllReactionsByUseId(userId);
        LOGGER.info("reactions fetched successfuly ");
        return new ResponseEntity<>(reactionDTOS, HttpStatus.OK);

    }

    @PostMapping("/{postId}")
    public ResponseEntity<ReactionDTO> addingReactionToAPost(
            @PathVariable long postId,
            @RequestBody ReactionDTO reactionDTO
    ){
        LOGGER.info("Adding reaction to post");
        ReactionDTO addedReaction = reactionService.addReactionToPost(postId, reactionDTO);
        return new ResponseEntity<>(addedReaction, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAReaction(@PathVariable Long id){
        LOGGER.info("deleting reaction " +id);
        reactionService.removeReactionFromAPost(id);
        return new ResponseEntity<>("reaction deleted successfully", HttpStatus.OK);
    }
}
