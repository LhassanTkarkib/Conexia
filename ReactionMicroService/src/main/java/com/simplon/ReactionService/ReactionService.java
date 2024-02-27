package com.simplon.ReactionService;

import org.springframework.stereotype.Service;

import java.util.List;

public interface ReactionService {
  List<ReactionDTO> getAllReactionsByPostId(Long postId);
  List<ReactionDTO> getAllReactionsByUseId(Long userId);
  ReactionDTO addReactionToPost(Long postId,ReactionDTO reactionDTO);
  void removeReactionFromAPost(Long id);
  ReactionDTO updateReaction(Long id,ReactionDTO reactionDTO);

}
