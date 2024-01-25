package com.ait.grooming.utils.request.auth;

import com.ait.grooming.dto.pet.PetDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Name is required")
    @Schema(example = "swagger")
    private String name;

    @Schema(example = "test")
    private String lastName;

    @Schema(example = "015217185893")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Schema(example = "swagger@example.com")
    private String email;

    @NotBlank(message = "Password is required")
    @Schema(example = "password1")
    private String password;

    private List<PetDto> pet;
}
