package org.mimage.dtos.request;

import jakarta.validation.constraints.NotNull;
import org.mimage.data.models.Post;

public record PostRequest(
        @NotNull(message = "Token should not be empty")
        String token,
        @NotNull(message = "id should not be empty")
        String id,

        @NotNull(message = "post should not be empty")
        Post post
) {
}
