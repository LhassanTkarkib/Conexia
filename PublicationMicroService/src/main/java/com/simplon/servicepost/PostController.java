package com.simplon.servicepost;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin("*")
@RequestMapping("/posts")
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
    public ResponseEntity<PostDTO> addPost(@RequestParam String postDTOJson,@RequestParam("file") MultipartFile file){
        if (postDTOJson == null) throw new IllegalArgumentException("Post cannot be null");
        PostDTO postDTO=null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            postDTO = objectMapper.readValue(postDTOJson, PostDTO.class);
            // Your existing logic here
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(postDTO);
        return new ResponseEntity<>(postService.addPost(null,null), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO>  getPostById(@PathVariable(value = "id") long id){
        return new ResponseEntity<>(postService.getPost(id),HttpStatus.OK);
    }
    @GetMapping("/user/{iduser}")
    public ResponseEntity<List<PostDTO>>  getPostsByUser(@PathVariable(value = "iduser") long id){
        return new ResponseEntity<>(postService.getPostsByUser(id),HttpStatus.OK);
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
