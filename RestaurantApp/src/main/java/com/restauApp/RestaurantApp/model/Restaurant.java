package com.restauApp.RestaurantApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String address;

    private List<String> images;

    @Column(nullable = false)
    private Boolean isOpen;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false , updatable = false)
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

    public Long getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getImages() {
        return images;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setIsOpen(Boolean open) {
        this.isOpen = open;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        this.isDeleted = deleted;
    }
}
