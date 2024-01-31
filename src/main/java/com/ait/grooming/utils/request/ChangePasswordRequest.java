package com.ait.grooming.utils.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
    @Schema(example = "password1")
    private String currentPassword;
    @Schema(example = "password2")
    private String newPassword;
    @Schema(example = "password2")
    private String confirmationPassword;
}
