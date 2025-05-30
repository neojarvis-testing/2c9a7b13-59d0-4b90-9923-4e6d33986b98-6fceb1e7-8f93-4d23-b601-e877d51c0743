package com.examly.springappfeedback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springappfeedback.model.Feedback;
import com.examly.springappfeedback.response.ResponseHandler;
import com.examly.springappfeedback.service.FeedbackService;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
     
    private FeedbackService feedbackService;
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PreAuthorize("hasAnyRole('RECEPTIONIST', 'NURSE', 'DOCTOR')")
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllFeedbacks() {
        return ResponseHandler.generateResponse("Feedbacks fetched", HttpStatus.OK, this.feedbackService.getAllFeedbacks());
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/users/save")
    public ResponseEntity<Object> saveFeedback(@RequestBody Feedback feedback) {
        return ResponseHandler.generateResponse("Feedback added successfully", HttpStatus.CREATED, this.feedbackService.saveFeedback(feedback));
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<Object> getFeedbackByUserId(@PathVariable int userId) {
        return ResponseHandler.generateResponse("Feedback fetched", HttpStatus.CREATED, this.feedbackService.getFeedbacksByUserId(userId));
    }
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/users/{feedbackId}")
    public ResponseEntity<Object> deleteFeedback(@PathVariable int feedbackId) {
        return ResponseHandler.generateResponse("Feedback deleted successfully", HttpStatus.CREATED, this.feedbackService.deleteFeedback(feedbackId));
    }
}
