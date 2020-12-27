package com.company.carshowroomssimulatorspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"reviewDate"},
        allowGetters = true)
public class Rating implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "rating")
    private RatingEnum rating;
    @ManyToOne()
    @JoinColumn(name = "showroom_id", nullable = false)
    @JsonIgnore
    private CarShowroom showroom;
    private String description;

    @Transient
    private long showroomId;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date reviewDate;

    public Rating(RatingEnum rating, String description, Date reviewDate, CarShowroom carShowroom) {
        this.rating = rating;
        this.description = description;
        this.reviewDate = reviewDate;
        this.showroom = carShowroom;
    }

    public Rating(){

    }

    public long getShowroomId() {
        return showroomId;
    }

    public void setShowroomId(long showroomId) {
        this.showroomId = showroomId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public RatingEnum getRating() {
        return rating;
    }

    public void setRating(RatingEnum rating) {
        this.rating = rating;
    }

    public CarShowroom getShowroom() {
        return showroom;
    }

    public void setShowroom(CarShowroom showroom) {
        this.showroom = showroom;
    }
}
