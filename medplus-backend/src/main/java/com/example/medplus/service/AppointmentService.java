package com.example.medplus.service;

import com.example.medplus.dto.AppointmentRequest;
import com.example.medplus.model.Appointment;
import com.example.medplus.model.User;
import com.example.medplus.repository.AppointmentRepository;
import com.example.medplus.repository.UserRepository; // <-- make sure you have this
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository; // Inject UserRepository

    public Appointment bookAppointment(AppointmentRequest request, Authentication authentication) {
        // Get email/username from Authentication
        String email = authentication.getName();

        // Fetch your actual User entity from DB
        User patient = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Appointment appointment = new Appointment();
        appointment.setPatientId(patient.getId());
        appointment.setDoctorId(request.getDoctorId());
        appointment.setDateTime(request.getDateTime());
        appointment.setStatus("PENDING");
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getPatientAppointments(Authentication authentication) {
        String email = authentication.getName();
        User patient = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return appointmentRepository.findByPatientId(patient.getId());
    }

    public List<Appointment> getDoctorAppointments(Authentication authentication) {
        String email = authentication.getName();
        User doctor = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return appointmentRepository.findByDoctorId(doctor.getId());
    }

}
