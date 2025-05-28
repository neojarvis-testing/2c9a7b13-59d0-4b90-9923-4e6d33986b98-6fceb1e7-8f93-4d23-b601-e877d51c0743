package com.examly.springappmedicine.service;

import com.examly.springappmedicine.exception.MedicalHistoryIdNotFoundException;
import com.examly.springappmedicine.model.MedicalHistory;

public interface MedicalHistoryService {
    MedicalHistory addMedicalHistory(MedicalHistory medicalHistory);
    MedicalHistory getSpecificMedicalHistory(int medicalhistoryId) throws MedicalHistoryIdNotFoundException;
    MedicalHistory updateHistory(MedicalHistory medicalHistory) throws MedicalHistoryIdNotFoundException;
    MedicalHistory deleteMedicalHistory(int medicalhistoryId) throws MedicalHistoryIdNotFoundException;
    
}
