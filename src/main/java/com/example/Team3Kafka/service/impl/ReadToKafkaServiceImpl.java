package com.example.Team3Kafka.service.impl;

import com.example.Team3Kafka.entity.Employee;
import com.example.Team3Kafka.fileHandler.CSVFileHandler;
import com.example.Team3Kafka.fileHandler.JsonFileHandler;
import com.example.Team3Kafka.fileHandler.XMLFileHandler;
import com.example.Team3Kafka.service.ReadToKafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class ReadToKafkaServiceImpl implements ReadToKafkaService {

    @Autowired
    private KafkaTemplate<String, Employee> kafkaTemplate;

    private static final String TOPIC = "Employee_kafka_json";

    public KafkaTemplate<String, Employee> getKafkaTemplate() {
        return kafkaTemplate;
    }

    @Autowired
    JsonFileHandler jsonFileHandler;
    @Autowired
    XMLFileHandler xmlFileHandler;
    @Autowired
    CSVFileHandler csvFileHandler;

    @Override
    public void readTOKafka() {

        jsonFileHandler.start();
        xmlFileHandler.start();
        csvFileHandler.start();

        try {
            csvFileHandler.join();
            jsonFileHandler.join();
            xmlFileHandler.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
