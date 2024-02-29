package com.simplon.servicepost;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private long postId;
    private String content;
    private long userId;
    private String datePost;
    private boolean deleted = false;
}
