package com.simplon.ReactionService;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReactionDTO {
    private Long id;
    private Long userId;
    private Long postId;
    private TypeReaction typeReaction;
}
