package com.simplon.postservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePost  implements Ipost {
    private PostRepository postRepository;
    private Mapperservice mapper;


    @Autowired
    ServicePost(PostRepository postRepository, Mapperservice mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }
    @Override
    public PostDTO addPost(PostDTO post) {
        if (post == null) throw new IllegalArgumentException("Post cannot be null");
        System.out.println(mapper.toEntity(post));
        Post postEntity = postRepository.save(mapper.toEntity(post));
        return this.mapper.toDTO(postEntity);
    }

    @Override
    public boolean deletePost(long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null)throw new IllegalArgumentException("Post not found");
        //post.setDeleted(true);
        post=postRepository.save(post);
        return true;
    }

    @Override
    public PostDTO updatePost(PostDTO post,long postId) {
        Post postEntity = postRepository.findById(postId).orElse(null);
        if (postEntity == null) throw new IllegalArgumentException("Post not found");
        postEntity = postRepository.save(postEntity);
        return this.mapper.toDTO(postEntity);
    }

    @Override
    public PostDTO getPost(long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) throw new IllegalArgumentException("Post not found");
        return this.mapper.toDTO(post);

    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts=postRepository.findByDeletedFalse();
        return posts.stream().map(mapper::toDTO).toList();
    }

    @Override
    public List<PostDTO> getPostsByUser(long userId) {
        List<Post> posts=postRepository.findByUserIdAndDeletedFalse(userId);
        if (posts == null) throw new IllegalArgumentException("Post not found");
        return posts.stream().map(mapper::toDTO).toList();
    }



}
