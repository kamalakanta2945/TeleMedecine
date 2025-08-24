package com.example.medplus.service;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.medplus.model.Consultation;
import com.example.medplus.model.ConsultationStatus;
import com.example.medplus.repository.ConsultationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationRepository consultationRepository;

    public Consultation book(String patientId, String doctorId, Instant scheduledAt, String notes) {
        Consultation consultation = Consultation.builder()
                .patientId(patientId)
                .doctorId(doctorId)
                .scheduledAt(scheduledAt)
                .notes(notes)
                .status(ConsultationStatus.SCHEDULED)
                .build();
        return consultationRepository.save(consultation);
    }

    public List<Consultation> patientList(String patientId) {
        return consultationRepository.findByPatientId(patientId);
    }

    public List<Consultation> doctorList(String doctorId) {
        return consultationRepository.findByDoctorId(doctorId);
    }

    public Consultation updateStatus(String id, ConsultationStatus status) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation not found"));
        consultation.setStatus(status);
        return consultationRepository.save(consultation);
    }
}