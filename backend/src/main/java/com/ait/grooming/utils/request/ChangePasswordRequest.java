package com.ait.grooming.utils.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordRequest {
    @Schema(example = "password1")
    private String currentPassword;
    @Schema(example = "password2")
    private String newPassword;
    @Schema(example = "password2")
    private String confirmationPassword;
}
