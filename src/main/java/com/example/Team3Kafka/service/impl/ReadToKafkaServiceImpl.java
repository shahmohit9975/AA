package com.example.Team3Kafka.service.impl;

import com.example.demoJsonToKafka.JsonFileHandler;
import com.example.demoJsonToKafka.service.ReadToKafkaService;
import org.springframework.stereotype.Service;


@Service
public class ReadToKafkaServiceImpl implements ReadToKafkaService {

    @Override
    public void readTOKafka() {
        JsonFileHandler jsonFileHandler=new JsonFileHandler();
        jsonFileHandler.start();

    }
}
