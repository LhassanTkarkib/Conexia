package com.simplon.postservice;


import java.util.List;

public interface Ipost {

    PostDTO addPost(PostDTO post);
    boolean deletePost(long postId);
    PostDTO updatePost(PostDTO post,long postId);
    PostDTO getPost(long postId);
     List<PostDTO> getAllPosts();
     List<PostDTO> getPostsByUser(long userId);

}
