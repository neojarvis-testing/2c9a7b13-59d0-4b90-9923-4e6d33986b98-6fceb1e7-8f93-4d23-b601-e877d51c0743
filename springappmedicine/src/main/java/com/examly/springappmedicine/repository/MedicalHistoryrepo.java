package com.examly.springappmedicine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springappmedicine.model.MedicalHistory;

@Repository
public interface  MedicalHistoryrepo extends JpaRepository<MedicalHistory, Integer> {
    
}
