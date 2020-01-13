package com.example.Team3Kafka.controller;

import com.example.Team3Kafka.entity.Employee;
import com.example.Team3Kafka.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("kafka")
public class EmployeeController {
    @Autowired
    private KafkaTemplate<String, Employee> kafkaTemplate;

    private static final String TOPIC = "Kafka_Employee";

    @GetMapping("/publish/{name}")
    public String post(@PathVariable("name") final String name) {

        kafkaTemplate.send(TOPIC, new Employee("fname", name, new Date(), 1));

        return "Published successfully";
    }
}
