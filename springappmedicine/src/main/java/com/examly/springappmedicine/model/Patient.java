package com.examly.springappmedicine.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patientId")
    private int patientId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String phoneNumber;

    //@Email(message= "Please provide correct email")
    private String email;
    private String insuranceDetails;
    private String status;
    

    public Patient(){

    }
    public Patient(int patientId,String firstName,String lastName,LocalDate dateOfBirth,String gender, String address,String phoneNumber,String email,String insuranceDetails,String status){
        this.patientId=patientId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.dateOfBirth=dateOfBirth;
        this.gender=gender;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.insuranceDetails=insuranceDetails;
        this.status=status;
    }

    public void  setPatientId(int patientId){
        this.patientId=patientId;
    }

    public int getPatientId(){
        return patientId;
    }

    public void setFirstName(String firstName){
        this.firstName=firstName;
    }

    public String  getFirstName(){
        return  firstName;
    }

    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public String getLastName(){
        return lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth=dateOfBirth;
    }

    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }

    public void setGender(String gender){
        this.gender=gender;
    }
    
    public String getGender(){
        return gender;
    }

    public void setAddress(String address){
        this.address=address;
    }

    public String getAddress(){
        return address;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getEmail(){
        return email;
    }

    public void setInsuranceDetails(String insuranceDetails){
        this.insuranceDetails=insuranceDetails;
    }

    public String getInsuranceDetails(){
        return insuranceDetails;
    }

    public void setStatus(String status){
        this.status=status;
    }

    public String getStatus(){
        return status;
    }

    // public String toString(){
    //     return this.firstName+" "+this.lastName;
    // }

}

