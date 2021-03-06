package com.appointmentbooking.booking.AppointmentService.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.appointmentbooking.booking.AppointmentService.model.Appointment;
import com.google.common.base.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long>{

    @Query(value = "SELECT * FROM appointment WHERE docId=?1", nativeQuery = true)
    List<Appointment> findBydocId(String docId);
    @Query(value = "DELETE FROM appointment WHERE docId=?1",nativeQuery = true)
    @Modifying
    @Transactional
    void deleteBydocId(String docId);

    @Query(value = "SELECT * FROM appointment WHERE pat_username=?1", nativeQuery = true)
	List<Appointment> findByPatUsername(String user);

    
}
