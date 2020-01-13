package com.example.Team3Kafka.listener;


import com.example.Team3Kafka.entity.Employee;
import com.example.Team3Kafka.entity.Employee;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "Kafka_Employee", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }

    @KafkaListener(topics = "Kafka_Employee_json", groupId = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(Employee employee) {
//        System.out.println("id:" + employee.getId());
//        System.out.println("name:" + employee.getName());
        System.out.println("Consumed JSON Message: " + employee);
    }
}