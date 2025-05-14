package org.mimage.data.repositories;

import org.mimage.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User findByEmail(String email);
    Boolean existsByEmail(String email);

}

