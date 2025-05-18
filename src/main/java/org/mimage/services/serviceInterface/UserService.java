package org.mimage.services.serviceInterface;

import jakarta.validation.Valid;
import org.mimage.data.models.Post;
import org.mimage.dtos.request.PostRequest;

public interface UserService {
    Post createPost(@Valid PostRequest postRequest);

}
