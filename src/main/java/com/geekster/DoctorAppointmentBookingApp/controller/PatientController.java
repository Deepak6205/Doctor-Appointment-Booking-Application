package com.geekster.DoctorAppointmentBookingApp.controller;

import com.geekster.DoctorAppointmentBookingApp.model.*;
import com.geekster.DoctorAppointmentBookingApp.model.dto.ScheduleAppointmentDTO;
import com.geekster.DoctorAppointmentBookingApp.model.dto.SignInInputDto;
import com.geekster.DoctorAppointmentBookingApp.model.dto.AuthenticationInputDto;
import com.geekster.DoctorAppointmentBookingApp.service.AppointmentService;
import com.geekster.DoctorAppointmentBookingApp.service.DoctorService;
import com.geekster.DoctorAppointmentBookingApp.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    AppointmentService appointmentService;


    @Autowired
    DoctorService doctorService;


    //sign up
     // means simply make a user.
    @PostMapping("patient/signup")
    public String patientSignUp(@Valid @RequestBody Patient patient){
        return patientService.patientSignUp(patient);
    }


    //sign in
    @PostMapping("patient/signIn")
    public  String patientSignIn(@RequestBody SignInInputDto signInInput){
        return patientService.patientSignIn(signInInput);
    }


    //sign out: iska matlab token delete ho jayega who has signed in.
    @DeleteMapping("patient/signOut")
    public String patientSignOut(@RequestBody AuthenticationInputDto authInfo){
        return patientService.patientSignOut(authInfo);

    }

    //schedule an appointment
    @PostMapping("patient/appointment/schedule")
    public String scheduleAppointment(@RequestBody ScheduleAppointmentDTO scheduleAppointmentDTO){
        return appointmentService.ScheduleAppointment(scheduleAppointmentDTO.getAuthInfo(),scheduleAppointmentDTO.getAppointment());
    }
    // cancel appointment:
    @DeleteMapping("patient/appointment/{appointmentId}/cancel")
    public String cancelAppointment(@RequestBody AuthenticationInputDto authInfo, @PathVariable Integer appointmentId){
        return appointmentService.cancelAppointment(authInfo,appointmentId);

    }

    //custom finder:

    @GetMapping("doctors/qualification/{qual}/or/specialization/{special}")
    public List<Doctor> getDoctorsByQualificationOrSpec(@RequestBody AuthenticationInputDto authInfo,@PathVariable Qualification qual,@PathVariable Specialization spec){
        return doctorService.getDoctorsByQualificationOrSpec(authInfo,qual,spec);
    }

}
