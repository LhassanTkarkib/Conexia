package com.simplon.ReactionService;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReactionServiceImplTest {

    @Mock
    private ReactionRepository reactionRepository;

    @Mock
    private ReactionMapper reactionMapper;

    @InjectMocks
    private ReactionServiceImpl reactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllReactionsByPostId() {
        // Arrange
        Long postId = 1L;
        ReactionDTO reactionDTO = new ReactionDTO(1L, 2L, 1L, TypeReaction.LIKE);
        List<Reaction> reactions = Arrays.asList(new Reaction(), new Reaction());

        when(reactionRepository.findByPostId(postId)).thenReturn(reactions);
        when(reactionMapper.toDTO(any(Reaction.class))).thenReturn(reactionDTO);

        List<ReactionDTO> result = reactionService.getAllReactionsByPostId(postId);

        assertEquals(reactions.size(), result.size());
        verify(reactionMapper, times(reactions.size())).toDTO(any(Reaction.class));
    }

    @Test
    void getAllReactionsByUserId() {
        Long userId = 2L;
        ReactionDTO reactionDTO = new ReactionDTO(1L, 2L, 1L, TypeReaction.LIKE);
        List<Reaction> reactions = Arrays.asList(new Reaction(), new Reaction());

        when(reactionRepository.findByUserId(userId)).thenReturn(reactions);
        when(reactionMapper.toDTO(any(Reaction.class))).thenReturn(reactionDTO);

        List<ReactionDTO> result = reactionService.getAllReactionsByUseId(userId);

        assertEquals(reactions.size(), result.size());
        verify(reactionMapper, times(reactions.size())).toDTO(any(Reaction.class));
    }

    @Test
    void addReactionToPost() {
        ReactionDTO reactionDTO = new ReactionDTO(1L, 2L, 1L, TypeReaction.LIKE);
        Reaction reaction = new Reaction();
        when(reactionRepository.findReactionByUserIdAndPostId(anyLong(), anyLong()))
                .thenReturn(Optional.of(new Reaction()));
        when(reactionMapper.toEntity(reactionDTO)).thenReturn(reaction);
        when(reactionMapper.toDTO(reaction)).thenReturn(reactionDTO);
        verify(reactionRepository, never()).save(reaction);
        verify(reactionMapper, never()).toDTO(reaction);
    }


    @Test
    void removeReactionFromAPost_Success() {
        // Arrange
        Long reactionId = 1L;
        Reaction existingReaction = new Reaction();
        existingReaction.setId(reactionId);

        when(reactionRepository.findById(reactionId)).thenReturn(Optional.of(existingReaction));

        // Act
        assertDoesNotThrow(() -> reactionService.removeReactionFromAPost(reactionId));

        // Assert
        verify(reactionRepository, times(1)).deleteById(reactionId);
    }

    @Test
    void removeReactionFromAPost_WhenReactionNotFound() {
        // Arrange
        Long reactionId = 1L;

        when(reactionRepository.findById(reactionId)).thenReturn(Optional.empty());

        // Act and Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> reactionService.removeReactionFromAPost(reactionId));

        assertEquals("Reaction with id " + reactionId + " not found", exception.getMessage());
        verify(reactionRepository, never()).deleteById(anyLong());
    }


    @Test
    void updateReaction() {
        Long id = 1L;
        ReactionDTO reactionDTO = new ReactionDTO(1L, 2L, 1L, TypeReaction.LIKE);
        Reaction existingReaction = new Reaction();
        existingReaction.setId(id);

        when(reactionRepository.findById(id)).thenReturn(Optional.of(existingReaction));
        when(reactionMapper.toEntity(reactionDTO)).thenReturn(existingReaction);
        when(reactionMapper.toDTO(existingReaction)).thenReturn(reactionDTO);

        ReactionDTO result = reactionService.updateReaction(id, reactionDTO);

        assertEquals(reactionDTO, result);
        verify(reactionRepository, times(1)).findById(id);
        verify(reactionMapper, times(1)).toEntity(reactionDTO);
        verify(reactionMapper, times(1)).toDTO(existingReaction);
    }
}
