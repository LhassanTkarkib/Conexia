package com.simplon.servicepost;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Media {
    private long fileId;
    private String name;
    private String fileUrl;
    private TypeFile typeFile;
    private String dateCreation;
    private long postId;
}