package com.geekster.DoctorAppointmentBookingApp.model.dto;

import com.geekster.DoctorAppointmentBookingApp.model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleAppointmentDTO {

    AuthenticationInputDto authInfo;
    Appointment appointment;
}
