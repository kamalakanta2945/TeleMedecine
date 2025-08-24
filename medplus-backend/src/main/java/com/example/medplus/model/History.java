package com.example.medplus.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "histories")
public class History {
    @Id
    private String id;
    private String patientId;
    private String appointmentId;
    private String prescriptionId;
    private String summary;  // E.g., "Consultation for headache, prescribed paracetamol"
}