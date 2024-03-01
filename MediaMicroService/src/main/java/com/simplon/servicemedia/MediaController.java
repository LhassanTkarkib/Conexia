package com.simplon.servicemedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/media")
public class MediaController {
    private IMedia mediaService;
    @Autowired
    public MediaController(IMedia mediaService) {
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
    public ResponseEntity<Map<String,Boolean>> deleteMedia(@PathVariable(value = "fileId") long fileId) {
        Map<String,Boolean> response=new HashMap<>();
        if(mediaService.deleteMedia(fileId))
            response.put("Deleted",true);
        else
            response.put("Deleted",true);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
