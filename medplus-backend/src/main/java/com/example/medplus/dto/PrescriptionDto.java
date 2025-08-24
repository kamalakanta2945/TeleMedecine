package com.example.medplus.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionDto {

    private String id;
    private String appointmentId;
    private String doctorId;
    private String patientId;
    private String issuedDate;
    private List<String> medications;
    private String instructions;
}