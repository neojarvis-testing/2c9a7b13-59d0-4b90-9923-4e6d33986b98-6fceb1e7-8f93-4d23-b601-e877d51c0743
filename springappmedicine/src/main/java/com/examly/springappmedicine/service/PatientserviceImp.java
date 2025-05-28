package com.examly.springappmedicine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springappmedicine.exception.PatientIdNotFoundException;
import com.examly.springappmedicine.model.Patient;
import com.examly.springappmedicine.repository.Patientrepo;


import java.util.Optional;

@Service
public class PatientserviceImp implements Patientservice {

    private final Patientrepo patientrepo;

    @Autowired
    public PatientserviceImp (Patientrepo patientrepo){
        this.patientrepo=patientrepo;
    }



    @Override
    public Patient addPatient(Patient patient){
        return patientrepo.save(patient);
    }

    @Override
    public Patient getSpecificPatient(int patientId) throws PatientIdNotFoundException{
        Optional<Patient>  optionalpatient = patientrepo.findById(patientId);
        if(optionalpatient.isEmpty()){
            throw new PatientIdNotFoundException("PatientId not found ");
        }
        else{
            return optionalpatient.get();
        }
    }

    @Override
    public Patient  updatePatient(Patient patient) throws PatientIdNotFoundException{
        Optional<Patient> optionalpatient = patientrepo.findById(patient.getPatientId());
        if(optionalpatient.isEmpty()){
            throw new PatientIdNotFoundException("PatientId not found");
        }
        else{
            return patientrepo.save(patient);
        }
    }

    @Override
    public Patient deletePatient(int patientId) throws PatientIdNotFoundException{
        Optional<Patient> optionalpatient = patientrepo.findById(patientId);
        if(optionalpatient.isEmpty()){
            throw new PatientIdNotFoundException("PatientId not found");
        }else{
            Patient patient = optionalpatient.get();
            patientrepo.deleteById(patient.getPatientId());
            return patient;
        }

    }


    
}
