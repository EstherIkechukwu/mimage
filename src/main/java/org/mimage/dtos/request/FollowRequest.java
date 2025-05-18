package org.mimage.dtos.request;

import jakarta.validation.constraints.NotNull;

public record FollowRequest(
        @NotNull(message = "token should not be empty")
        String token,
        @NotNull(message = "id should not be empty")
        String id,
        @NotNull(message = "follower id should not be null")
        String followerId,
        @NotNull(message = "following id should not be null")
        String followingId
) {
}
