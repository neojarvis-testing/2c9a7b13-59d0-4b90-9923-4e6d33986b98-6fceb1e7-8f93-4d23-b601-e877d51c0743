package com.examly.springappfeedback.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.examly.springappfeedback.exception.FeedbackNotFoundException;
import com.examly.springappfeedback.model.Feedback;
import com.examly.springappfeedback.model.User;
import com.examly.springappfeedback.repository.FeedbackRepo;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepo feedbackRepo;
    public FeedbackServiceImpl(FeedbackRepo feedbackRepo) {
        this.feedbackRepo = feedbackRepo;
    }

    private List<Feedback> convert(List<Feedback> list) {
        for(Feedback feedback: list) {
            User user = new User();
            user.setUserId(feedback.getUserId());
            feedback.setUser(user);
        }
        return list;
    }
    @Override
    public Feedback saveFeedback(Feedback feedback) {
        return this.feedbackRepo.save(feedback);
    }
    @Override
    public List<Feedback> getAllFeedbacks() {
        List<Feedback> list = this.feedbackRepo.findAll();
        return convert(list);
    }
    @Override
    public List<Feedback> getFeedbacksByUserId(int userId) {
        List<Feedback> list = this.feedbackRepo.findByUserId(userId);
        return convert(list);
    }
    @Override
    public Feedback deleteFeedback(int feedbackId) throws FeedbackNotFoundException {
        Optional<Feedback> feedback = this.feedbackRepo.findById(feedbackId);
        if(feedback.isEmpty()) {
            throw new FeedbackNotFoundException("Feedback not found");
        }
        this.feedbackRepo.deleteById(feedbackId);
        return feedback.get();
    }
}
