package org.mimage.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
public class Post {
    @Id
    private String id;

    private String title;

    private String imageUrl;

    private LocalDateTime createdAt;

    public Post(){
        this.createdAt = LocalDateTime.now();
    }
}

