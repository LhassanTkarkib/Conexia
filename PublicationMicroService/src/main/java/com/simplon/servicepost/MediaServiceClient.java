package com.simplon.servicepost;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "MEDIA")
public interface MediaServiceClient {
    @PostMapping("/media/{Id}")
    Media addmedia(@RequestParam("file") MultipartFile file, @PathVariable("Id") long mediaId);
    /*@GetMapping("/media/{Id}")
    Media addmedia(@RequestParam("file") MultipartFile file, @PathVariable("Id") String productId);*/
}
