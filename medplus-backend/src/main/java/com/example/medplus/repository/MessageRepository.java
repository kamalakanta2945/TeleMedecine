package com.example.medplus.repository;

import com.example.medplus.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByAppointmentId(String appointmentId);
}