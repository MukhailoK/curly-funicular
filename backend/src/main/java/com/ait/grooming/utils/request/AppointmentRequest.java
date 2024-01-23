package com.ait.grooming.utils.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequest {
//    @JsonProperty("access_token")
//    private String accessToken;
//   @Schema(example = "client1@example.com")
//    private String clientEmail;
    @Schema(example = "1")
    private Integer groomingId;
 //   private Integer petId;
    @Schema(example = "024-02-08 10:00:00.000000")
    private LocalDateTime dateTimeStart;
 //   private LocalDateTime dateTimeEnd;
 //   private String status;
}
