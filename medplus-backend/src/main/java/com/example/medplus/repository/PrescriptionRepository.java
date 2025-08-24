package com.example.medplus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.medplus.model.Prescription;

public interface PrescriptionRepository extends MongoRepository<Prescription, String> {
    // updated method name to match field in Prescription
    List<Prescription> findByPatientIdOrderByIssuedDateDesc(String patientId);

    Optional<Prescription> findByAppointmentId(String appointmentId);
}
