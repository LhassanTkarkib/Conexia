package com.simplon.media;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@FeignClient(name = "MEDIA")
public interface MediaServiceClient {
    @GetMapping
     ResponseEntity<Iterable<MediaDTO>> getAllMedia();
    @PostMapping("/api/v1/media/{postId}")
     ResponseEntity<MediaDTO> addMedia(@RequestParam("file") MultipartFile file, @PathVariable(value = "postId") long postId);

    @DeleteMapping("/api/v1/media/{fileId}")
     ResponseEntity<Map<String,Boolean>> deleteMedia(@PathVariable(value = "fileId") long fileId);
}
