package com.example.Team3Kafka.fileHandler;

import com.example.Team3Kafka.entity.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonFileHandler extends Thread {

    public static final String DATE_OF_BIRTH_FORMAT = "MM/dd/yy";
//    @Autowired
//    KafkaTemplate<String, Employee> KafkaJsontemplate;
//    String TOPIC_NAME = "items-topic";

    @Override
    public void run() {

        //ArrayList<com.coviam.Employee.Employee> employeeList=new ArrayList<>();
        FileReader reader = null;
        try {
            reader = new FileReader("employee.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JSONParser jsonParser = new JSONParser();

        Object jsonData = null;
        try {
            jsonData = jsonParser.parse(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = (JSONArray) jsonData;

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            Employee employee = new Employee();
            employee.setFirstName((String) jsonObject.get("firstName"));
            employee.setLastName((String) jsonObject.get("lastName"));
            try {
                employee.setDateOfBirth(parseDate((String) jsonObject.get("dateOfBirth")));
            } catch (ParseException | java.text.ParseException e) {
                e.printStackTrace();
            }
            employee.setExperience(Integer.parseInt(String.valueOf(jsonObject.get("experience"))));

            //KafkaJsontemplate.send(TOPIC_NAME,employee);
            System.out.println("JSON-->" + employee);
        }


    }


    private static Date parseDate(String date) throws ParseException,
            java.text.ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_OF_BIRTH_FORMAT);
        return formatter.parse(date);
    }
}
