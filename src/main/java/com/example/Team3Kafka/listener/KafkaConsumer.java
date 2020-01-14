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

    private static int addedrecordCount = 0;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeDocumentRepository employeeDocumentRepository;

    @KafkaListener(topics = "Kafka_Example10", groupId = "group_id_string")
    public void consumeString(String msg) {
        System.out.println("consume string msg : " + msg);
    }

    @KafkaListener(topics = "Employee_kafka_json", groupId = "group_id_json_1", containerFactory = "userkafkaListenerContainerFactory")
    public void consumeUserJSON(Employee employee) {
        employeeRepository.save(employee);
        System.out.println("consume json msg for Postgres: " + employee);
        System.out.println("count value :" + addedrecordCount++);
    }

    @KafkaListener(topics = "Employee_kafka_json", groupId = "group_id_json_1", containerFactory = "userkafkaListenerContainerFactory")
    public void consumeUserJSON2(Employee employee) {
        EmployeeDocument employeeDocument = new EmployeeDocument();
        BeanUtils.copyProperties(employee, employeeDocument);
        employeeDocumentRepository.insert(employeeDocument);
        System.out.println("consume json msg for MongoDB: " + employee);
        System.out.println("count value :" + addedrecordCount++);
    }
}