package com.example.medplus.service;

import com.example.medplus.model.History;
import com.example.medplus.model.User;
import com.example.medplus.repository.HistoryRepository;
import com.example.medplus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final UserRepository userRepository; // inject repository to fetch custom User

    // Called after consultation completes
    public void addToHistory(String patientId, String appointmentId, String prescriptionId, String summary) {
        History history = new History();
        history.setPatientId(patientId);
        history.setAppointmentId(appointmentId);
        history.setPrescriptionId(prescriptionId);
        history.setSummary(summary);
        historyRepository.save(history);
    }

    public List<History> getPatientHistory(Authentication authentication) {
        String email = authentication.getName(); // Spring Security username (email)
        User patient = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return historyRepository.findByPatientId(patient.getId());
    }
}
