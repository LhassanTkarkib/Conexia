package com.simplon.servicepost;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private long postId;
    private String content;
    private long userId;
    private long fileId;
    private String datePost;
    private boolean deleted = false;
}
