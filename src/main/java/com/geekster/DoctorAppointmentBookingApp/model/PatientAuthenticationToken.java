package com.geekster.DoctorAppointmentBookingApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="PAuthentication")
public class PatientAuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String tokenValue;
    private LocalDateTime tokeCreationTime;

    //each token should be linked with a patient.

    @OneToOne
    @JoinColumn(name = "fk_ patient_id")
    Patient patient;

    public PatientAuthenticationToken(Patient patient) {
        this.patient = patient;
        this.tokeCreationTime = LocalDateTime.now();
        this.tokenValue = UUID.randomUUID().toString();
    }
}
