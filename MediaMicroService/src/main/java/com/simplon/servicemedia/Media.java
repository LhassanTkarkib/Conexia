package com.simplon.servicemedia;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "media")
@Data
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fileId;
    private String name;
    private TypeFile typeFile;
    private Date dateCreation;
}
