package com.simplon.ReactionService;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReactionServiceImpl implements ReactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReactionServiceImpl.class);
    private final ReactionRepository reactionRepository;
    private final ReactionMapper reactionMapper;
    private final PublicationClient publicationClient;

    @Autowired
    public ReactionServiceImpl(ReactionRepository reactionRepository, ReactionMapper reactionMapper,PublicationClient publicationClient) {
        this.reactionMapper = reactionMapper;
        this.reactionRepository = reactionRepository;
        this.publicationClient=publicationClient;
    }

    @Override
    public Page<ReactionDTO> getAllReactionsByPostId(Long postId,Pageable pageable) {
        try {
            LOGGER.info("Fetching all reactions by post id");
            Page<Reaction> reactions =  reactionRepository.findByPostId(postId,pageable);
            return reactions.map(reactionMapper::toDTO);

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching all reaction by post id", e);
            throw e;
        }
    }

    @Override
    public Page<ReactionDTO> getAllReactionsByUseId(Long userId, Pageable pageable) {
        try {
            LOGGER.info("Récupération de toutes les réactions par userId");
            Page<Reaction> reactions =reactionRepository.findByUserId(userId, pageable);
            return reactions.map(reactionMapper::toDTO);
        } catch(Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la récupération de toutes les réactions par userId", e);
            throw e;
        }
    }

    @Override
    public ReactionDTO addReactionToPost(ReactionDTO reactionDTO) {
        try {
            LOGGER.info("Service: Adding a new reaction to the post");
            // check if the user has already reacted to that post before adding a new reaction
            Reaction reaction = reactionMapper.toEntity(reactionDTO);
            Reaction newReaction = reactionRepository.save(reaction);
            return reactionMapper.toDTO(newReaction);

        } catch (Exception e) {
            LOGGER.error("An error has occurred while adding a new reaction to the post", e);
            throw e;
        }
    }


    @Override
    public ReactionDTO updateReaction(ReactionDTO reactionDTO) {
        try {
            LOGGER.info("Mise à jour de la réaction");
            Reaction existingReaction = reactionMapper.toEntity(reactionDTO);
            Optional<Reaction> existingReactionOptional = reactionRepository.findReactionByUserIdAndPostId(existingReaction.getUserId(), existingReaction.getPostId());

            if (existingReactionOptional.isPresent()) {
                Reaction existingReactionFromDb = existingReactionOptional.get();
                existingReactionFromDb.setTypeReaction(reactionDTO.getTypeReaction());
                existingReactionFromDb.setDateDeReaction(reactionDTO.getDateDeReaction());
                return reactionMapper.toDTO(reactionRepository.save(existingReactionFromDb));
            } else {
                return addReactionToPost(reactionDTO);
            }
        } catch (Exception e) {
            LOGGER.error("A problem has occurred while trying to update this reaction", e);
            throw new RuntimeException("Failed to update reaction", e);
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
}
