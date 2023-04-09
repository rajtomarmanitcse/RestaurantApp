package com.restauApp.RestaurantApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Restaurant> restaurants;

    private Long noOfPersons;

    private String startTime;

    @Column(nullable = false,updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-mm-dd HH:MM:SS")
    private String createdDate;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-mm-dd HH:MM:SS")
    private String modifiedDate;

    @PrePersist
    protected void onCreate(){
        createdDate = modifiedDate = Timestamp.from(ZonedDateTime.now(ZoneId.of("Asia/Kolkata")).toInstant()).toString();
    }
    @PreUpdate
    protected void onUpdate(){
        modifiedDate = Timestamp.from(ZonedDateTime.now(ZoneId.of("Asia/Kolkata")).toInstant()).toString();
    }

    public Long getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public List<Restaurant> getTable() {
        return restaurants;
    }

    public Long getNoOfPersons() {
        return noOfPersons;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setNoOfPersons(Long noOfPersons) {
        this.noOfPersons = noOfPersons;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setTable(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
