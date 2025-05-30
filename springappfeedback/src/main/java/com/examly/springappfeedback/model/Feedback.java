package com.examly.springappfeedback.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Feedback {
    
    public Feedback() {
        this.date = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedbackId;
    private String feedbackText;
    private LocalDateTime date;
    @Transient
    private User user;
    private int userId;

    public Feedback(int feedbackId, String feedbackText, LocalDateTime date, User user, int userId) {
        this.feedbackId = feedbackId;
        this.feedbackText = feedbackText;
        this.date = date;
        this.user = user;
        this.userId = userId;
    }

    public void setFeedbackId(int feedbackId) {this.feedbackId = feedbackId;}
    public void setFeedbackText(String feedbackText) {this.feedbackText = feedbackText;}
    public void setDate(LocalDateTime date) {this.date = LocalDateTime.now();}
    public void setUser(User user) {
        this.user = user;
        if(user != null) {
            this.userId = user.getUserId();
        }
    }
    public void setUserId(int userId) {this.userId = userId;}

    public int getFeedbackId() {return this.feedbackId;}
    public String getFeedbackText() {return this.feedbackText;}
    public LocalDateTime getDate() {return this.date;}
    public User getUser() {return this.user;}
    public int getUserId() {return this.userId;}

    @Override
    public String toString() {
        return "ID: " + feedbackId + ", Text: " + feedbackText + ", Date: " + date + ", UserID: " + userId;
    }
}
