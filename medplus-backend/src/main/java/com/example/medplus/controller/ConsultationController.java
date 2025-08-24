package com.example.medplus.controller;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.medplus.dto.BookConsultationRequest;
import com.example.medplus.dto.UpdateConsultationStatusRequest;
import com.example.medplus.model.Consultation;
import com.example.medplus.model.ConsultationStatus;
import com.example.medplus.service.ConsultationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/consultations")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    @PostMapping("/book")
    public Consultation book(@RequestBody BookConsultationRequest request) {
        return consultationService.book(
                request.getPatientId(),
                request.getDoctorId(),
                Instant.parse(request.getScheduledAt()),
                request.getNotes()
        );
    }
    

    @GetMapping("/patient/{patientId}")
    public List<Consultation> getPatientConsultations(@PathVariable String patientId) {
        return consultationService.patientList(patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Consultation> getDoctorConsultations(@PathVariable String doctorId) {
        return consultationService.doctorList(doctorId);
    }

    @PatchMapping("/{id}/status")
    public Consultation updateStatus(@PathVariable String id,
                                     @RequestBody UpdateConsultationStatusRequest request) {
        return consultationService.updateStatus(id, request.getStatus());
    }
}



