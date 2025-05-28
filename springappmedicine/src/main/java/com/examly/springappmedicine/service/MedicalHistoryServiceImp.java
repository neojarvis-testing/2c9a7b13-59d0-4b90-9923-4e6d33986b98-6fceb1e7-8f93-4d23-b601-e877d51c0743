package com.examly.springappmedicine.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springappmedicine.exception.MedicalHistoryIdNotFoundException;
import com.examly.springappmedicine.exception.PatientIdNotFoundException;
import com.examly.springappmedicine.model.MedicalHistory;
import com.examly.springappmedicine.model.Patient;
import com.examly.springappmedicine.repository.MedicalHistoryrepo;
import com.examly.springappmedicine.repository.Patientrepo;

@Service
public class MedicalHistoryServiceImp implements MedicalHistoryService {

    private final MedicalHistoryrepo medicalHistoryrepo;
    private final Patientrepo patientrepo;

    @Autowired
    public MedicalHistoryServiceImp(MedicalHistoryrepo medicalHistoryrepo, Patientrepo patientrepo){
        this.medicalHistoryrepo=medicalHistoryrepo;
        this.patientrepo=patientrepo;
    }
    
    @Override
    public MedicalHistory addMedicalHistory (MedicalHistory medicalHistory) throws PatientIdNotFoundException{
        int pat_id=medicalHistory.getPatient().getPatientId();
        Optional<Patient> optionalpatient =patientrepo.findById(pat_id);
        if(optionalpatient.isEmpty()){
            throw  new PatientIdNotFoundException("patiend id not found for saving medical history");
        }else{
            return medicalHistoryrepo.save(medicalHistory);
        }

        
    }

    @Override
    public MedicalHistory  getSpecificMedicalHistory (int medicalhistoryId) throws MedicalHistoryIdNotFoundException{
        Optional<MedicalHistory> optionalmedicalhistory = medicalHistoryrepo.findById(medicalhistoryId);
        if(optionalmedicalhistory.isEmpty()){
            throw new MedicalHistoryIdNotFoundException("medical History not found");
        }else{
            return optionalmedicalhistory.get();
        }
    }

    @Override
    public MedicalHistory updateHistory (MedicalHistory medicalHistory) throws MedicalHistoryIdNotFoundException ,PatientIdNotFoundException{
        int pat_id=medicalHistory.getPatient().getPatientId();
        Optional<Patient> optionalpatient =patientrepo.findById(pat_id);
        Optional<MedicalHistory> optionalmedicalhistory = medicalHistoryrepo.findById(medicalHistory.getMedicalHistoryId());
        if(optionalmedicalhistory.isEmpty()){
            throw new MedicalHistoryIdNotFoundException("medical history not found");
        }else{
            if(optionalpatient.isEmpty()){
                throw new PatientIdNotFoundException("patiend id not found so medical history didnot updated ");
            }else{
            return medicalHistoryrepo.save(medicalHistory);
            }
        }
    }

    @Override
    public MedicalHistory deleteMedicalHistory(int medicalhistoryId) throws MedicalHistoryIdNotFoundException{
        Optional<MedicalHistory> optionalmedicalhistory = medicalHistoryrepo.findById(medicalhistoryId);
        if(optionalmedicalhistory.isEmpty()){
            throw new MedicalHistoryIdNotFoundException("medical history not found");
        }else{
            MedicalHistory deletedmedicalHistoryDetails = optionalmedicalhistory.get();
            medicalHistoryrepo.deleteById(deletedmedicalHistoryDetails.getMedicalHistoryId());
            return deletedmedicalHistoryDetails;
        }
    }

}
