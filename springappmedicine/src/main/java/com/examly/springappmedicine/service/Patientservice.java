package com.examly.springappmedicine.service;

import com.examly.springappmedicine.exception.PatientIdNotFoundException;
import com.examly.springappmedicine.model.Patient;

public interface Patientservice {
     Patient addPatient(Patient patient);
     Patient getSpecificPatient(int patientId) throws PatientIdNotFoundException;
     Patient updatePatient (Patient patient) throws PatientIdNotFoundException;
     Patient deletePatient (int patientId) throws PatientIdNotFoundException;


}
