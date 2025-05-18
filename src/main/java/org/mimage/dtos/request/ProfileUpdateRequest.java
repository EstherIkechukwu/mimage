package org.mimage.dtos.request;

import jakarta.validation.constraints.NotNull;
import org.mimage.data.models.Profile;

public record ProfileUpdateRequest(
        @NotNull
        String token,
        @NotNull
        String id,
        @NotNull(message = "Profile shouldn't be empty")
        Profile profile) {
}
