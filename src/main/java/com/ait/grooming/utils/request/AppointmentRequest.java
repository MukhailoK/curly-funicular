package com.ait.grooming.utils.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequest {

    @Schema(example = "1")
    @NotNull(message = "groomingId can't be null")
    private Integer groomingId;

    @Schema(example = "2024-02-08T10:00:00")
    @NotNull(message = "dateTimeStart can't be null")
    private LocalDateTime dateTimeStart;
}
