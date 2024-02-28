package com.simplon.ReactionService;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReactionServiceImpl implements ReactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReactionServiceImpl.class);
    private final ReactionRepository reactionRepository;
    private final ReactionMapper reactionMapper;

    @Autowired
    public ReactionServiceImpl(ReactionRepository reactionRepository, ReactionMapper reactionMapper) {
        this.reactionMapper = reactionMapper;
        this.reactionRepository = reactionRepository;
    }

    @Override
    public List<ReactionDTO> getAllReactionsByPostId(Long postId) {
        try {
            LOGGER.info("Fetching all reactions by post id");
            List<Reaction> reactions = reactionRepository.findByPostId(postId);
            return reactions.stream().map(reactionMapper::toDTO).collect(Collectors.toList());

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching all reaction by post id", e);
            throw e;
        }
    }

    @Override
    public List<ReactionDTO> getAllReactionsByUseId(Long userId) {
       try {
           LOGGER.info("fetching all reactions by userId");
           List<Reaction> reactions =reactionRepository.findByUserId(userId);
          return reactions.stream().map(reactionMapper::toDTO).collect(Collectors.toList());
       }catch(Exception e){
           LOGGER.error("Error occurred while fetching all reaction by user id", e);
           throw e;
       }
    }


    @Override
    public ReactionDTO addReactionToPost(ReactionDTO reactionDTO) {
        try {
            LOGGER.info("Service: Adding a new reaction to the post");
            // check if the user has already reacted to that post before adding a new reaction
            Reaction reaction = reactionMapper.toEntity(reactionDTO);
            Optional<Reaction> reactionAlreadyExist = reactionRepository.findReactionByUserIdAndPostId(reaction.getUserId(), reaction.getPostId());

            if (reactionAlreadyExist.isPresent()) {
                throw new EntityNotFoundException("The user already reacted to that post");
            }
            Reaction newReaction = reactionRepository.save(reaction);
            return reactionMapper.toDTO(newReaction);
        } catch (EntityNotFoundException e) {
            LOGGER.warn("User already reacted to the post: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            LOGGER.error("An error has occurred while adding a new reaction to the post", e);
            throw e;
        }
    }


    @Override
    public void removeReactionFromAPost(Long id) {
        try {
            LOGGER.info("removing reaction");
            Optional<Reaction> existingReaction = reactionRepository.findById(id);
            if (existingReaction.isEmpty()) {
                throw new EntityNotFoundException("Reaction with id " + id + " not found");
            }
            reactionRepository.deleteById(id);
        }catch(Exception e){
            LOGGER.error("a problem has occured while trying to remove this reaction");
            throw e ;
        }

    }

    @Override
    public ReactionDTO updateReaction(Long id,ReactionDTO reactionDTO) {
        try {
            LOGGER.info("Updating reaction");
            Optional<Reaction> existingReaction = reactionRepository.findById(id);
            if (existingReaction.isEmpty()) {
                throw new EntityNotFoundException("Reaction with id " + id + " not found");
            }
            Reaction reaction = reactionMapper.toEntity(reactionDTO);
           reaction.setTypeReaction(existingReaction.get().getTypeReaction());
            return reactionMapper.toDTO(reaction);
        } catch (EntityNotFoundException e) {
            LOGGER.error("Reaction not found: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            LOGGER.error("A problem has occurred while trying to update this reaction", e);
            throw new RuntimeException("Failed to update reaction", e);
        }
    }
}
