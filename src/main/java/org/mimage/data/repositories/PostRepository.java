package org.mimage.data.repositories;

import org.mimage.data.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post,String> {

    Post findByTitle(String title);
}
