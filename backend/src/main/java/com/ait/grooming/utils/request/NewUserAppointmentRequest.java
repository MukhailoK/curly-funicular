package com.ait.grooming.utils.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewUserAppointmentRequest extends AppointmentRequest{
    //User новый
    private String name;
    private String lastName;
    private String userName;
    private String email;
    private String phone;
    //добавить собаку
    private String nameDog;
    private String breed;
    private String specialNotes;


}
