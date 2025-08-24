package com.example.medplus.service;

import com.example.medplus.model.Message;
import com.example.medplus.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final MessageRepository messageRepository;

    public Message saveMessage(String senderId, String receiverId, String appointmentId, String content) {
        Message message = new Message();
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setAppointmentId(appointmentId);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public List<Message> getChatHistory(String appointmentId) {
        return messageRepository.findByAppointmentId(appointmentId);
    }
}