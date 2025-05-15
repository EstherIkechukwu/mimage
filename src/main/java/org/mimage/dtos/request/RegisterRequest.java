package org.mimage.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(@NotNull(message = "email should not be null")
                              @Size(min = 14, max = 25, message = "email should not be less than 14")
                              String email,

                              @NotNull(message = "password should not be null")
                              @Size(min = 6, max = 15, message = "password length should be greater than 6 and less than 15" )
                              String password
) {
}
