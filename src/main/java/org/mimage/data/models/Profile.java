package org.mimage.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String imageUrl;
    private List<String> followersId = new ArrayList<>();
    private List<String> followingId = new ArrayList<>();
}
