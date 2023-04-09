package com.restauApp.RestaurantApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false,length = 25)
    private String firstName;
    @Column(length = 25)
    private String middleName;
    @Column(nullable = false,length = 25)
    private String lastName;
    @Column(nullable = false,length = 25)
    private String city;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mobileNo", referencedColumnName = "mobile")
    private Login login;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> booking;
    @Column(nullable = false)
    private Boolean isActive;
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

    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public List<Booking> getBooking() {
        return booking;
    }

    public void setBooking(List<Booking> booking) {
        this.booking = booking;
    }
}
