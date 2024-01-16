package com.ait.grooming.utils.maper.appointment;

import com.ait.grooming.dto.appointment.AppointmentDto;
import com.ait.grooming.model.Appointment;

import java.util.List;

//import static com.ait.grooming.utils.maper.client.ClientMapper.toClientDto;
//import static com.ait.grooming.utils.maper.employee.EmployeeMapper.toEmployeeDto;
import static com.ait.grooming.utils.maper.grooming.GroomingMapper.toGroomingDto;
import static com.ait.grooming.utils.maper.pet.PetMapper.toPetDto;
import static com.ait.grooming.utils.maper.user.UserMapper.toUserDto;

public class AppointmentMapper {
    public static AppointmentDto toAppointmentDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setStatus(appointment.getStatus());
        appointmentDto.setDateTimeStart(appointment.getDateTimeStart());
        appointmentDto.setDateTimeEnd(appointment.getDateTimeEnd());

        appointmentDto.setUserDto(toUserDto(appointment.getClient()));
    //   appointmentDto.setEmployeeDto(toEmployeeDto(appointment.getMaster()));

        appointmentDto.setGroomingDto(toGroomingDto(appointment.getGroomingService()));
        appointmentDto.setPetDto(toPetDto(appointment.getPet()));
        return appointmentDto;
    }

    public static List<AppointmentDto> allToAppointmentDto(List<Appointment> appointments) {
        return appointments.stream().map(AppointmentMapper::toAppointmentDto).toList();
    }
}
