package com.example.Team3Kafka.repository;

import com.example.Team3Kafka.entity.EmployeeDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDocumentRepository extends MongoRepository<EmployeeDocument, Integer> {
}
