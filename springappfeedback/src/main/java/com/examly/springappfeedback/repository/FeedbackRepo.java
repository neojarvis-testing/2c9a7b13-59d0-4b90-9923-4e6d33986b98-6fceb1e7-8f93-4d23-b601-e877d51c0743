package com.examly.springappfeedback.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.examly.springappfeedback.model.Feedback;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByUserId(int userId);
}
