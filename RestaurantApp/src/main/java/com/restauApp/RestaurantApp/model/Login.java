package com.restauApp.RestaurantApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
public class Login {

    @Id
    @Column(name = "mobile")
    private String mobileNo;

    private String password;

    @OneToOne(mappedBy = "login")
    private User user;

    @OneToOne(mappedBy = "login")
    private Restaurant restaurant;

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

    public String getMobileNo() {
        return mobileNo;
    }

    public String getPassWord() {
        return password;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
