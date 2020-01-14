package com.example.Team3Kafka.listener;


import com.example.Team3Kafka.entity.Employee;
import com.example.Team3Kafka.entity.EmployeeDocument;
import com.example.Team3Kafka.repository.EmployeeDocumentRepository;
import com.example.Team3Kafka.repository.EmployeeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    public KafkaConsumer() {
        // TODO Auto-generated constructor stub
    }

    private static int count = 0;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeDocumentRepository employeeDocumentRepository;

    @KafkaListener(topics = "Kafka_Example10", groupId = "group_id_string")
    public void consumeString(String msg) {
        System.out.println("consume string msg : " + msg);
    }

    @KafkaListener(topics = "Kafka_Example_json20", groupId = "group_id_json", containerFactory = "userkafkaListenerContainerFactory")
    public void consumeUserJSON(Employee employee) {
        if (count < 150) {
            employeeRepository.save(employee);
            System.out.println("consume json msg for Postgres: " + employee);

        } else {

            EmployeeDocument employeeDocument = new EmployeeDocument();
            BeanUtils.copyProperties(employee, employeeDocument);
            employeeDocumentRepository.insert(employeeDocument);
            System.out.println("consume json msg for MongoDB: " + employee);
        }
        System.out.println("count value :" + count++);


    }
}