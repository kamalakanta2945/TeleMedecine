package com.example.medplus.service;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.medplus.dto.PrescriptionDto;
import com.example.medplus.model.Prescription;
import com.example.medplus.model.User;
import com.example.medplus.repository.AppointmentRepository;
import com.example.medplus.repository.PrescriptionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final AppointmentRepository appointmentRepository;

    public Prescription issuePrescription(PrescriptionDto dto, Authentication authentication) {
        User doctor = (User) authentication.getPrincipal(); // JWT doctor
        var appointmentOpt = appointmentRepository.findById(dto.getAppointmentId());

        if (appointmentOpt.isEmpty()) {
            throw new RuntimeException("Appointment not found");
        }

        ObjectId appointmentDoctorId = new ObjectId(appointmentOpt.get().getDoctorId());
        ObjectId jwtDoctorId = new ObjectId(doctor.getId());

        if (!appointmentDoctorId.equals(jwtDoctorId)) {
            throw new RuntimeException("Unauthorized");
        }
       
        
//
        Prescription prescription = new Prescription();
        prescription.setAppointmentId(dto.getAppointmentId());
        prescription.setDoctorId(jwtDoctorId.toHexString());
        prescription.setPatientId(appointmentOpt.get().getPatientId());
        prescription.setMedications(dto.getMedications());
        prescription.setInstructions(dto.getInstructions());
        prescription.setIssuedDate(LocalDateTime.now());

        return prescriptionRepository.save(prescription);
    }

    public Prescription getPrescription(String appointmentId) {
        return prescriptionRepository.findByAppointmentId(appointmentId).orElse(null);
    }
}