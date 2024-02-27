package com.simplon.servicemedia;

import lombok.Data;

import java.util.Date;

@Data
public class MediaDTO {
    private long fileId;
    private String name;
    private TypeFile typeFile;
    private Date dateCreation;
    private long postId;
}
