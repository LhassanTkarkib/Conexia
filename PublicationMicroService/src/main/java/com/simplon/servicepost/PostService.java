package com.simplon.servicepost;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PostService implements Ipost {
    private PostRepository postRepository;
    private MapperConfig mapper;
    private final MediaServiceClient mediaServiceClient;
    @Autowired
    PostService(PostRepository postRepository, MapperConfig mapper, MediaServiceClient mediaServiceClient){
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.mediaServiceClient = mediaServiceClient;
    }
    @Override
    public PostDTO addPost(PostDTO post,MultipartFile file) {
        if (post == null) throw new IllegalArgumentException("Post cannot be null");
        if(file != null){
            Media media = mediaServiceClient.addmedia(file, post.getPostId());
            System.out.println(media);
        }
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        post.setDatePost(now.format(dateFormat));
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
