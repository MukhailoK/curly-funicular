package com.ait.grooming.utils.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Schema(example = "swagger")
    private String name;
    @Schema(example = "test")
    private String lastName;
    @Schema(example = "userName")
    private String userName;
    @Schema(example = "swager@example.com")
    private String email;
    @Schema(example = "password1")
    private String password;
    @Schema(example = "015217185893")
    private String phone;
    @Schema(example = "Mite, Berlin")
    private String address;
}
