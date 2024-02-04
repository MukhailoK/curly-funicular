package com.ait.grooming.utils.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewUserAppointmentRequest extends AppointmentRequest{
   @Schema(example = "guest")
    private String name;
   @Schema(example = "guestLastName")
    private String lastName;
   @Schema(example = "guest@mail.com")
    private String email;
   @Schema(example = "123456789")
    private String phone;
    @Schema(example = "Pufi")
    private String nameDog;
    @Schema(example = "Labrador")
    private String breed;
    @Schema(example = "can bite")
    private String specialNotes;

}
