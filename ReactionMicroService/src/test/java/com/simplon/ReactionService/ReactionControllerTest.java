package com.simplon.ReactionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReactionControllerTest {

    @Mock
    private ReactionService reactionService;

    @InjectMocks
    private ReactionController reactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllReactionsByPostId() {

        long postId = 1L;
        List<ReactionDTO> mockReactionDTOs = Arrays.asList(new ReactionDTO(), new ReactionDTO());
        when(reactionService.getAllReactionsByPostId(postId)).thenReturn(mockReactionDTOs);
        ResponseEntity<List<ReactionDTO>> response = reactionController.getAllReactionsByPostId(postId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockReactionDTOs, response.getBody());
    }

    @Test
    void getAllReactionsByUserId() {

        long userId = 2L;
        List<ReactionDTO> mockReactionDTOs = Arrays.asList(new ReactionDTO(), new ReactionDTO());
        when(reactionService.getAllReactionsByUseId(userId)).thenReturn(mockReactionDTOs);
        ResponseEntity<List<ReactionDTO>> response = reactionController.getAllReactionsByUserId(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockReactionDTOs, response.getBody());
    }

    @Test
    void addingReactionToAPost() {

        ReactionDTO inputReactionDTO = new ReactionDTO();
        ReactionDTO mockAddedReactionDTO = new ReactionDTO();
        when(reactionService.addReactionToPost(inputReactionDTO)).thenReturn(mockAddedReactionDTO);
        ResponseEntity<ReactionDTO> response = reactionController.addingReactionToAPost(inputReactionDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockAddedReactionDTO, response.getBody());
    }

    @Test
    void deleteAReaction() {
        Long reactionId = 1L;
        ResponseEntity<String> response = reactionController.deleteAReaction(reactionId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("reaction deleted successfully", response.getBody());
        verify(reactionService, times(1)).removeReactionFromAPost(reactionId);
    }
}
