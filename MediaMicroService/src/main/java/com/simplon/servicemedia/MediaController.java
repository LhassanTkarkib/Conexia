package com.simplon.servicemedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/media")
public class MediaController {
    private MediaService mediaService;
    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping
    public ResponseEntity<Iterable<MediaDTO>> getAllMedia() {
        return new ResponseEntity<>(mediaService.listMedia(), HttpStatus.OK);
    }
    @PostMapping("/{postId}")
    public ResponseEntity<MediaDTO> addMedia(@RequestParam("file") MultipartFile file, @PathVariable(value = "postId") long postId) throws FileStorageException {
        return new ResponseEntity<>(mediaService.addMedia(file, postId), HttpStatus.CREATED);
    }

   @DeleteMapping("/{fileId}")
    public ResponseEntity<Void> deleteMedia(@PathVariable(value = "fileId") long fileId) {
        mediaService.deleteMedia(fileId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
