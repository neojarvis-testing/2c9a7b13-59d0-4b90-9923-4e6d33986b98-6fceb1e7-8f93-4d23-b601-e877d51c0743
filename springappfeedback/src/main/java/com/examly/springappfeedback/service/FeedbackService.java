package com.examly.springappfeedback.service;

import java.util.List;

import com.examly.springappfeedback.exception.FeedbackNotFoundException;
import com.examly.springappfeedback.model.Feedback;

public interface FeedbackService {
    Feedback saveFeedback(Feedback feedback);
    List<Feedback> getAllFeedbacks();
    List<Feedback> getFeedbacksByUserId(int userId);
    Feedback deleteFeedback(int feedbackId) throws FeedbackNotFoundException;
}
