package com.example.Team3Kafka.fileHandler;

import com.example.Team3Kafka.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class CSVFileHandler extends Thread {
    @Autowired
    private KafkaTemplate<String, Employee> kafkaTemplate;
    private static final String TOPIC = "Employee_kafka_json";

    @Override
    public void run() {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String line = "";
        String splitBy = ",";

        try {

            BufferedReader br = new BufferedReader(new FileReader("employee.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                Employee emp = new Employee();
                String[] employee = line.split(splitBy);    // use comma as separator
                emp.setFirstName(employee[0]);
                emp.setLastName(employee[1]);
                emp.setDateOfBirth(format.parse(employee[2]));
                emp.setExperience(Integer.parseInt(employee[3]));
                kafkaTemplate.send(TOPIC, emp);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException p) {
            p.printStackTrace();
        }
    }
}
