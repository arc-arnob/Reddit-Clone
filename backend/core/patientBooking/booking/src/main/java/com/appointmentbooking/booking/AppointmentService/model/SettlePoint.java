package com.appointmentbooking.booking.AppointmentService.model;
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
public class SettlePoint { // This can be used for mongodb nearBy; // Admin Task
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settleId;
    private String lon;
    private String lat;
}
