package com.example.medplus.dto;

import com.example.medplus.model.ConsultationStatus;

import lombok.Data;

@Data
public class UpdateConsultationStatusRequest {
    private ConsultationStatus status; // Example: "COMPLETED" or "CANCELLED"
}
