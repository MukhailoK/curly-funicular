package com.ait.grooming.utils.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewUserAppointmentRequest extends AppointmentRequest {

    @Schema(example = "guest")
    @NotBlank(message = "name is require")
    private String name;

    @Schema(example = "guestLastName")
    private String lastName;

    @Schema(example = "guest@mail.com")
    @NotBlank(message = "email is require")
    private String email;

    @Schema(example = "123456789")
    private String phone;

    @Schema(example = "Pufi")
    @NotBlank(message = "Dog name is require")
    private String nameDog;

    @Schema(example = "Labrador")
    @NotBlank(message = "breed is require")

    private String breed;

    @Schema(example = "can bite")
    private String specialNotes;

}
