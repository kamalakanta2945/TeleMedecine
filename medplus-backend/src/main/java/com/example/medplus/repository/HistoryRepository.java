package com.example.medplus.repository;

import com.example.medplus.model.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface HistoryRepository extends MongoRepository<History, String> {
    List<History> findByPatientId(String patientId);
}