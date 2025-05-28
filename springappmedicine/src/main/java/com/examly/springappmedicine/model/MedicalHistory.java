package com.examly.springappmedicine.model;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;




@Entity
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int medicalHistoryId;
    private String diagnosis;
    private String treatment;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="patientId", nullable = false)
    private Patient patient;

    public MedicalHistory(){

    }

    public MedicalHistory(String diagnosis,String treatment,LocalDateTime date,Patient patient){
        //this.medicalHistoryId=medicalHistoryId;
        this.diagnosis=diagnosis;
        this.treatment=treatment;
        this.date=date;
        this.patient = patient;
    }

    public void setMedicalHistoryId(int medicalHistoryId){
        this.medicalHistoryId=medicalHistoryId;
    }

    public int getMedicalHistoryId(){
        return medicalHistoryId;
    }
    
    public void setDiagnosis(String diagnosis){
        this.diagnosis=diagnosis;
    }

    public String getDiagnosis(){
        return diagnosis;
    }

    public void setTreatment(String treatment){
        this.treatment=treatment;
    }

    public String getTreatment (){
        return treatment;
    }

    public void  setDate(LocalDateTime date){
        this.date=date;
    }

    public LocalDateTime getDate(){
        return date;
    }

    public  void setPatient(Patient patient){
        this.patient=patient;
    }

    public Patient getPatient(){
        return patient;
    }


    
}
