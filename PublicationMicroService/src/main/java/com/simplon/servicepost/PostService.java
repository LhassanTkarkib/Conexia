package com.simplon.servicepost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements Ipost {
    private PostRepository postRepository;
    private MapperConfig mapper;


    @Autowired
    PostService(PostRepository postRepository, MapperConfig mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }
    @Override
    public PostDTO addPost(PostDTO post) {
        if (post == null) throw new IllegalArgumentException("Post cannot be null");
        Post postEntity = postRepository.save(mapper.modelMapper().map(post, Post.class));
        return this.mapper.modelMapper().map(postEntity, PostDTO.class);
    }

    @Override
    public boolean deletePost(long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null)throw new IllegalArgumentException("Post not found");
        post.setDeleted(true);
        return postRepository.save(post).isDeleted();
    }

    @Override
    public PostDTO updatePost(PostDTO post,long postId) {
        Post postEntity = postRepository.findById(postId).orElse(null);
        if (postEntity == null) throw new IllegalArgumentException("Post not found");
        postEntity = postRepository.save(this.mapper.modelMapper().map(post, Post.class));
        return this.mapper.modelMapper().map(postEntity, PostDTO.class);
    }

    @Override
    public PostDTO getPost(long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) throw new IllegalArgumentException("Post not found");
        return this.mapper.modelMapper().map(post, PostDTO.class);

    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts=postRepository.findByDeletedFalse();
        return posts.stream().map(c->this.mapper.modelMapper().map(c,PostDTO.class)).toList();
    }

    @Override
    public List<PostDTO> getPostsByUser(long userId) {
        List<Post> posts=postRepository.findByUserIdAndDeletedFalse(userId);
        if (posts == null) throw new IllegalArgumentException("Post not found");
        return posts.stream().map(c->this.mapper.modelMapper().map(c,PostDTO.class)).toList();
    }

    public boolean harddeletePost(long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null)throw new IllegalArgumentException("Post not found");
        postRepository.delete(post);
        return true;
    }

}
