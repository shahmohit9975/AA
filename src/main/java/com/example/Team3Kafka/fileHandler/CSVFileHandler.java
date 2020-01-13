package com.example.Project1;

import com.example.Project1.entity.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CSVFileHandler extends Thread {
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
                System.out.println(emp);



            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException p) {
            p.printStackTrace();
        }
    }
}
