package com.examly.springappfeedback.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Feedback {
    
    public Feedback() {
        this.date = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedbackId;
    @NotBlank(message = "Feedback text is mandatory")
    private String feedbackText;
    @NotBlank(message = "Date is mandatory")
    private LocalDateTime date;
    private User user;

    public Feedback(int feedbackId, String feedbackText, LocalDateTime date, User user) {
        this.feedbackId = feedbackId;
        this.feedbackText = feedbackText;
        this.date = date;
        this.user = user;
    }

    public void setFeedbackId(int feedbackId) {this.feedbackId = feedbackId;}
    public void setFeedbackText(String feedbackText) {this.feedbackText = feedbackText;}
    public void setDate(LocalDateTime date) {this.date = date;}
    public void setUser(User user) {this.user = user;}

    public int getFeedbackId() {return this.feedbackId;}
    public String getFeedbackText() {return this.feedbackText;}
    public LocalDateTime getDate() {return this.date;}
    public User getUser() {return this.user;}
}
