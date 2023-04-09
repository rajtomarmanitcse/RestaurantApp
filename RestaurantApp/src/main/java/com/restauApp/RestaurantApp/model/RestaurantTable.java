package com.restauApp.RestaurantApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tableId;

    private Long tableNumber;

    private Long maxSize;

    private Boolean isOccupied;

    @ManyToOne
    @JoinColumn(name = "restaurantId", nullable = false)
    private Restaurant restaurant;

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

    public Long getTableId() {
        return tableId;
    }

    public Long getTableNumber() {
        return tableNumber;
    }

    public Long getMaxSize() {
        return maxSize;
    }

    public Boolean getOccupied() {
        return isOccupied;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setTableNumber(Long tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setMaxSize(Long maxSize) {
        this.maxSize = maxSize;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

}
