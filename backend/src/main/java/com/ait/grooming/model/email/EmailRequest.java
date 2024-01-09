package com.ait.grooming.model.email;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailRequest {

    private String to;
    private String subject;
    private String text;


}
