package com.example.medplus.controller;

import com.example.medplus.dto.PrescriptionDto;
import com.example.medplus.model.Prescription;
import com.example.medplus.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping("/issue")
    public ResponseEntity<Prescription> issue(@RequestBody PrescriptionDto dto, Authentication authentication) {
        return ResponseEntity.ok(prescriptionService.issuePrescription(dto, authentication));
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<Prescription> get(@PathVariable String appointmentId) {
        return ResponseEntity.ok(prescriptionService.getPrescription(appointmentId));
    }	
}
