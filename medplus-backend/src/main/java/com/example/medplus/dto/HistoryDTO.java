package com.example.medplus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HistoryDTO {
    private String appointmentId;
    private String prescriptionId;
    private String summary;
}
