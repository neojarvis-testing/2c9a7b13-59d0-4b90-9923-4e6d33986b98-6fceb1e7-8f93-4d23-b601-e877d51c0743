package com.examly.springappmedicine.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springappmedicine.model.Patient;




@Repository
public interface Patientrepo extends JpaRepository<Patient , Integer> {

    
}
