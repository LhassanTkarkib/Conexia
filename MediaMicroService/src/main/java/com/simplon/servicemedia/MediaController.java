package com.simplon.servicemedia;

import org.springframework.stereotype.Controller;

@Controller
public class MediaController {
    private MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }
}
