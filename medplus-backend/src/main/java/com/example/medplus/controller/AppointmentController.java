package com.example.medplus.controller;

import com.example.medplus.dto.AppointmentRequest;
import com.example.medplus.model.Appointment;
import com.example.medplus.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity<Appointment> book(@RequestBody AppointmentRequest request, Authentication authentication) {
        return ResponseEntity.ok(appointmentService.bookAppointment(request, authentication));
    }

    @GetMapping("/patient")
    public ResponseEntity<List<Appointment>> getPatientAppointments(Authentication authentication) {
        return ResponseEntity.ok(appointmentService.getPatientAppointments(authentication));
    }

    @GetMapping("/doctor")
    public ResponseEntity<List<Appointment>> getDoctorAppointments(Authentication authentication) {
        return ResponseEntity.ok(appointmentService.getDoctorAppointments(authentication));
    }
}