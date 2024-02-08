package com.ait.grooming.utils.maper.booking;

import com.ait.grooming.dto.booking.BookingDto;
import com.ait.grooming.model.Appointment;

import java.util.List;
public class BookingMapper {

    public static BookingDto convertToBookingDto(Appointment appointment) {
        BookingDto Bookingdto = new BookingDto();
        Bookingdto.setId(appointment.getId());
        Bookingdto.setDateTimeStart(appointment.getDateTimeStart());
        Bookingdto.setStatus(appointment.getStatus());
        return Bookingdto;
    }

    public static List<BookingDto> allToAppointmentDto(List<Appointment> appointments) {
        return appointments.stream().map(BookingMapper::convertToBookingDto).toList();
    }
}
