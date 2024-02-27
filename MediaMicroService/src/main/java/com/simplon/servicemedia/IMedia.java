package com.simplon.servicemedia;

import java.util.List;

public interface IMedia {
     MediaDTO addMedia(MediaDTO mediaDTO);
     MediaDTO updateMedia(MediaDTO mediaDTO);
     boolean deleteMedia(long id);
     List<MediaDTO> listMedia();

}
