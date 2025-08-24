package com.example.medplus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.medplus.model.Consultation;




public interface ConsultationRepository extends MongoRepository<Consultation, String> {
    List<Consultation> findByPatientId(String patientId);
    List<Consultation> findByDoctorId(String doctorId);
    Optional<Consultation> findById(String id);
}