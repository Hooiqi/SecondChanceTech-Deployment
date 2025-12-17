// Review.java
package org.secondchancetech.model;

public class Review {
    private int reviewId;
    private int starNumbers;
    private int userId;
    private String comment;

    public int getReviewId() { return reviewId; }
    public void setReviewId(int reviewId) { this.reviewId = reviewId; }
    public int getStarNumbers() { return starNumbers; }
    public void setStarNumbers(int starNumbers) { this.starNumbers = starNumbers; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
