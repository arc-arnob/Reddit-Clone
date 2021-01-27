package com.appointmentbooking.booking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DoctorSpeciality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long docSpecId;

    private String specName;
    private String description;
}
