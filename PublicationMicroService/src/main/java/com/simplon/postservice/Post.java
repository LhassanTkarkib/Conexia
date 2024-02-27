package com.simplon.postservice;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;
    private String content;
    private Date datePost;
    private long userId;
    private long fileId;
    private boolean deleted = false;

}
