package org.mimage.dtos.response;

import org.mimage.data.models.Profile;

public record AuthResponse (String token, String id, String email, Profile profile) {


}
