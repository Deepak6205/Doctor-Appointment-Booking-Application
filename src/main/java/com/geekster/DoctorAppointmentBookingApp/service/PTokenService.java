package com.geekster.DoctorAppointmentBookingApp.service;

import com.geekster.DoctorAppointmentBookingApp.model.PatientAuthenticationToken;
import com.geekster.DoctorAppointmentBookingApp.model.dto.AuthenticationInputDto;
import com.geekster.DoctorAppointmentBookingApp.repo.IPTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PTokenService {

    @Autowired
    IPTokenRepo ipTokenRepo;

    public void createToken(PatientAuthenticationToken token) {
        ipTokenRepo.save(token);
    }

    public void deleteToken(String tokenValue) {
        PatientAuthenticationToken token = ipTokenRepo.findFirstByTokenValue(tokenValue);
        ipTokenRepo.delete(token);
    }

    public boolean authenticate(AuthenticationInputDto authInfo) {
        String email = authInfo.getEmail();
        String tokenValue = authInfo.getTokenValue();

        //find the actual token then get the connected patient then get its email and verify with passed email.
      // 1st way but yaha hume exception milegi.
        // return ipTokenRepo.findFirstByTokenValue(tokenValue).getPatient().getPatientEmail().equals(email);

        //correct way.
        PatientAuthenticationToken token = ipTokenRepo.findFirstByTokenValue(tokenValue);
        if(token != null){
            return token.getPatient().getPatientEmail().equals(email);
        }else{
            return false;
        }
    }
}
