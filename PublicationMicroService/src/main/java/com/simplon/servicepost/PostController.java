package com.simplon.servicepost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin("*")
@RequestMapping("/post")
public class PostController {

    private Ipost postService;
    @Autowired
    PostController(Ipost postService){
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getPosts(){
        List<PostDTO> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
    @PostMapping
    public ResponseEntity<PostDTO> addPost(@RequestBody PostDTO postDTO){
        if (postDTO == null) throw new IllegalArgumentException("Post cannot be null");
        return new ResponseEntity<>(postService.addPost(postDTO), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO>  getPostById(@PathVariable(value = "id") long id){
        return new ResponseEntity<>(postService.getPost(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO>  updatePost(@RequestBody PostDTO postDTO,@PathVariable(value = "id") long id){
        if (postDTO == null) throw new IllegalArgumentException("Post cannot be null");

        return new ResponseEntity<>(postService.updatePost(postDTO,id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>> deletePost(@PathVariable(value = "id") long id){
        Map<String,Boolean> response = Map.of("deleted",postService.deletePost(id));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
