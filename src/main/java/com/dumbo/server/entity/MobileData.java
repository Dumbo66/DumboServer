package com.dumbo.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MobileData {
    private Double latBd09ll;

    private Double lngBd09ll;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recordTime;

    private Float ph;

    private Float temperature;

    private Float dissolvedOxygen;

    private Float conductivity;

    private Float turbidity;

    //水质等级
    private char grade;

    public Double getLatBd09ll() {
        return latBd09ll;
    }

    public void setLatBd09ll(Double latBd09ll) {
        this.latBd09ll = latBd09ll;
    }

    public Double getLngBd09ll() {
        return lngBd09ll;
    }

    public void setLngBd09ll(Double lngBd09ll) {
        this.lngBd09ll = lngBd09ll;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Float getPh() {
        return ph;
    }

    public void setPh(Float ph) {
        this.ph = ph;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getDissolvedOxygen() {
        return dissolvedOxygen;
    }

    public void setDissolvedOxygen(Float dissolvedOxygen) {
        this.dissolvedOxygen = dissolvedOxygen;
    }

    public Float getConductivity() {
        return conductivity;
    }

    public void setConductivity(Float conductivity) {
        this.conductivity = conductivity;
    }

    public Float getTurbidity() {
        return turbidity;
    }

    public void setTurbidity(Float turbidity) {
        this.turbidity = turbidity;
    }

    public char getGrade() {
        float standard=temperature;
        if(standard>=0 && standard<14){
            grade='A';
        }else if (standard>=14 && standard<18){
            grade='B';
        }else if (standard>=18&& standard<22){
            grade='C';
        }else if (standard>=22 && standard<26){
            grade='D';
        }else if (standard>=26 && standard<30){
            grade='E';
        }else if (standard>=30){
            grade='F';
        }
        return grade;
    }
}