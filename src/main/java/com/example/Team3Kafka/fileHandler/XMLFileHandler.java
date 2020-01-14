package com.example.Team3Kafka.fileHandler;


import com.example.Team3Kafka.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class XMLFileHandler extends Thread {

    @Autowired
    private KafkaTemplate<String, Employee> kafkaTemplate;
    private static final String TOPIC = "Kafka_Example_json20";

    @Override
    public void run() {


        try {

            File file = new File("employee.xml");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("employee");

            for (int index = 0; index < nodeList.getLength(); index++) {
                Employee sendEmp = new Employee();
                Node node = nodeList.item(index);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    sendEmp.setFirstName(eElement.getElementsByTagName("firstName").item(0).getTextContent());
                    sendEmp.setLastName(eElement.getElementsByTagName("lastName").item(0).getTextContent());
                    Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(eElement.getElementsByTagName("dateOfBirth").item(0).getTextContent());
                    sendEmp.setDateOfBirth(date1);
                    sendEmp.setExperience(Integer.parseInt(eElement.getElementsByTagName("experience").item(0).getTextContent()));
                    kafkaTemplate.send(TOPIC, sendEmp);
//                    Emplist.getList().add(sendEmp);

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}