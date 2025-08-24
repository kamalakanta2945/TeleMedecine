package com.example.medplus.dto;

import lombok.Data;

@Data
public class BookConsultationRequest {
	
	 private String patientId;
	    private String doctorId;
	    private String scheduledAt; // ISO format (e.g., "2025-08-24T10:30:00Z")
	    private String notes;
	}

