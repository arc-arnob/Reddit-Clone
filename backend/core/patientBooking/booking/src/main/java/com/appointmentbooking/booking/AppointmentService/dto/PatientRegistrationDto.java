package com.appointmentbooking.booking.AppointmentService.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientRegistrationDto implements Serializable{
    
    private String pat_name;
    private String pat_phone;
    private String pat_dob;
    private String enrollDate;
    private String pat_gender;
    private String insured;

    // private Long appTypeId;
    // private Long docId;

}
