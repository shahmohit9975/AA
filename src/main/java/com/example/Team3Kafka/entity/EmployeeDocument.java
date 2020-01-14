package com.example.Team3Kafka.entity;

import javax.persistence.*;
import java.lang.annotation.Documented;
import java.util.Date;

import org.bson.types.ObjectId;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class EmployeeDocument {

    @Id
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private int experience;

    public EmployeeDocument(String firstName, String lastName, Date dateOfBirth, int experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.experience = experience;
    }

    public EmployeeDocument() {

    }

//    public ObjectId get_id() {
//        return _id;
//    }
//
//    public void set_id(ObjectId _id) {
//        this._id = _id;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", experience=" + experience +
                '}';
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }


}
