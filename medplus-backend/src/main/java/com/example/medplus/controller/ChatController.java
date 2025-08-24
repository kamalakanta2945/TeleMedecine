package com.example.medplus.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medplus.dto.MessageDto;
import com.example.medplus.model.Message;
import com.example.medplus.model.User;
import com.example.medplus.service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

//    private final ChatService chatService;
//    private final SimpMessagingTemplate messagingTemplate;
//
//    @MessageMapping("/send")
//    public void sendMessage(@Payload MessageDto dto, Authentication authentication) {
//        User user = (User) authentication.getPrincipal();
//        Message message = chatService.saveMessage(user.getId(), dto.getReceiverId(), dto.getAppointmentId(), dto.getContent());
//        // Send to receiver's queue
//        messagingTemplate.convertAndSendToUser(dto.getReceiverId(), "/queue/messages", message);
//    }
//
//    @GetMapping("/{appointmentId}")
//    public List<Message> getChat(@PathVariable String appointmentId) {
//        return chatService.getChatHistory(appointmentId);
//    }
	
	
	private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    // ✅ WebSocket messaging
    @MessageMapping("/send")
    public void sendMessage(@Payload MessageDto dto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Message message = chatService.saveMessage(
                user.getId(),
                dto.getReceiverId(),
                dto.getAppointmentId(),
                dto.getContent()
        );
        messagingTemplate.convertAndSendToUser(dto.getReceiverId(), "/queue/messages", message);
    }

    // ✅ Fetch chat history for an appointment
    @GetMapping("/{appointmentId}")
    public List<Message> getChat(@PathVariable String appointmentId) {
        return chatService.getChatHistory(appointmentId);
    }

    // ✅ Save message via REST (important for Postman testing)
    @PostMapping("/{appointmentId}")
    public Message saveChatMessage(
            @PathVariable String appointmentId,
            @RequestBody MessageDto dto,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        return chatService.saveMessage(
                user.getId(),
                dto.getReceiverId(),
                appointmentId,
                dto.getContent()
        );
    }
}
	
	
	
	
