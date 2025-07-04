package org.mimage.dtos.request;

import jakarta.validation.constraints.NotNull;
import org.mimage.data.models.Profile;

public record ProfileUpdateRequest(
        @NotNull(message = "token should not be empty")
        String token,
        @NotNull(message = "id should not be empty")
        String id,
        @NotNull(message = "Profile shouldn't be empty")
        Profile profile) {
}
