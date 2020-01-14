package com.example.Team3Kafka.controller;


import com.example.Team3Kafka.repository.EmployeeRepository;
import com.example.Team3Kafka.service.ReadToKafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class Contoller {


    @Autowired
    ReadToKafkaService readToKafkaService;


    @GetMapping("/read")
    public void read() {
        readToKafkaService.readTOKafka();
    }


}
