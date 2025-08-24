package com.example.medplus.dto;

import lombok.Data;

@Data
public class MessageDto {
    private String appointmentId;
    private String receiverId;
    private String content;
}