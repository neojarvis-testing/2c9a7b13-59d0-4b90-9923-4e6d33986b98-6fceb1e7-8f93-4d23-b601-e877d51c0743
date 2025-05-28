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

import com.examly.springappmedicine.exception.MedicalHistoryIdNotFoundException;
import com.examly.springappmedicine.model.MedicalHistory;
import com.examly.springappmedicine.responsehandler.ResponseHandler;
import com.examly.springappmedicine.service.MedicalHistoryService;

@RestController
@RequestMapping("api/medicalhistories")
public class MedicalHistorycontroller {

    private final MedicalHistoryService medicalHistoryService;

    @Autowired
    public MedicalHistorycontroller(MedicalHistoryService medicalHistoryService){
        this.medicalHistoryService=medicalHistoryService;
    }

    @PostMapping
    public ResponseEntity<Object> addMedicalHistoryDetails(@RequestBody MedicalHistory medicalHistory){
        return ResponseHandler.generateResponse("successfully medicalhistory added", HttpStatus.CREATED, medicalHistoryService.addMedicalHistory(medicalHistory));
    }

    @GetMapping("/{medicalhistoryId}")
    public ResponseEntity<Object> getSpecificMedicalHistoryDetails(@PathVariable int medicalhistoryId) throws MedicalHistoryIdNotFoundException{
        try{
            return ResponseHandler.generateResponse("successfully medical history fetched ", HttpStatus.OK, medicalHistoryService.getSpecificMedicalHistory(medicalhistoryId));
        }catch(MedicalHistoryIdNotFoundException e){
            return ResponseHandler.generateResponse("Unsuccessfully fetched medical histiry", HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/{medicalhistoryId}")
    public ResponseEntity<Object> updateMedicalHistoryDetails(@PathVariable int medicalhistoryId , @RequestBody MedicalHistory medicalHistory) throws MedicalHistoryIdNotFoundException{
        try{
            return ResponseHandler.generateResponse("successfully updated medical history", HttpStatus.OK, this.medicalHistoryService.updateHistory(medicalHistory));
        }catch(MedicalHistoryIdNotFoundException e){
            return ResponseHandler.generateResponse("Unable to update medical history", HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @DeleteMapping("/{medicalhistoryId}")
    public ResponseEntity<Object> deleteMedicalHistoryDetails(@PathVariable int medicalhistoryId) throws MedicalHistoryIdNotFoundException{
        try{
            return ResponseHandler.generateResponse("successfully deleted medical history", HttpStatus.OK, this.medicalHistoryService.deleteMedicalHistory(medicalhistoryId));
        }catch(MedicalHistoryIdNotFoundException e){
            return ResponseHandler.generateResponse("unsuccessfully in deleting medical history", HttpStatus.CONFLICT, e.getMessage());
        }
    }
    
}
