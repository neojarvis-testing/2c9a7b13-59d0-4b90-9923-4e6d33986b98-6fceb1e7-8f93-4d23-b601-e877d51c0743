package com.examly.springappmedicine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springappmedicine.responsehandler.ResponseHandler;
import com.examly.springappmedicine.service.Patientservice;
import com.examly.springappmedicine.exception.PatientIdNotFoundException;
import com.examly.springappmedicine.model.Patient;

@RestController
@RequestMapping("api/patients")
public class Patientcontroller {

    private final Patientservice patientservice;

    @Autowired
    public Patientcontroller (Patientservice patientservice){
        this.patientservice=patientservice;
    }

    //adding patient details 

    @PostMapping
    public ResponseEntity<Object> addPatient(@RequestBody Patient patient){
        return ResponseHandler.generateResponse("Patient deatils added successfully", HttpStatus.CREATED, this.patientservice.addPatient(patient));
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<Object> getSpecificPatientDetails(@PathVariable int patientId) throws PatientIdNotFoundException{
        try{
            return ResponseHandler.generateResponse("Deatils of a specific patient", HttpStatus.OK, this.patientservice.getSpecificPatient(patientId));
        }catch(PatientIdNotFoundException e){
            return ResponseHandler.generateResponse("unable to fetch details", HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<Object> updatePatientDetails(@PathVariable int patientId, @RequestBody Patient patient) throws PatientIdNotFoundException{
        try{
            return ResponseHandler.generateResponse("Patient details sucessfully updated", HttpStatus.OK, this.patientservice.updatePatient(patient));
        }catch(PatientIdNotFoundException e){
            return ResponseHandler.generateResponse("error occured while updating", HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<Object> deletePatientDetails(@PathVariable int patientId) throws PatientIdNotFoundException{
        try{
            return ResponseHandler.generateResponse(" deletion completed", HttpStatus.OK, this.patientservice.deletePatient(patientId));
        }catch(PatientIdNotFoundException e){
            return ResponseHandler.generateResponse("unable to delete patient details error occured", HttpStatus.CONFLICT, e.getMessage());
        }
    }
    
}
