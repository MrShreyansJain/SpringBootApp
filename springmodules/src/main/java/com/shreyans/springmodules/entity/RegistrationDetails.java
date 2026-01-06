package com.shreyans.springmodules.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="register_detail")
public class RegistrationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regId", nullable = false)
    Long registrationID;
    @Column(name="studId")
    Long studentID;
    Date registrationDate;
    String governmentID;
    boolean isNative;

    public Long getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(Long registrationID) {
        this.registrationID = registrationID;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getGovernmentID() {
        return governmentID;
    }

    public void setGovernmentID(String governmentID) {
        this.governmentID = governmentID;
    }

    public boolean isNative() {
        return isNative;
    }

    public void setNative(boolean aNative) {
        isNative = aNative;
    }
}
