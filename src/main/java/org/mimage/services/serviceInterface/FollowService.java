package org.mimage.services.serviceInterface;

import jakarta.validation.Valid;
import org.mimage.data.models.Post;
import org.mimage.dtos.request.FollowRequest;

public interface FollowService {
    void follow(@Valid FollowRequest followRequest);
    void unfollow(@Valid FollowRequest followRequest);
}
