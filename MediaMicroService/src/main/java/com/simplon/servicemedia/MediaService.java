package com.simplon.servicemedia;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService implements IMedia{
    private MediaRepository mediaRepository;
    MediaService(MediaRepository mediaRepository1) {
       this.mediaRepository = mediaRepository1;
    }


    @Override
    public MediaDTO addMedia(MediaDTO mediaDTO) {
        return null;
    }

    @Override
    public MediaDTO updateMedia(MediaDTO mediaDTO) {
        return null;
    }

    @Override
    public boolean deleteMedia(long id) {
        return false;
    }

    @Override
    public List<MediaDTO> listMedia() {
        return null;
    }
}
