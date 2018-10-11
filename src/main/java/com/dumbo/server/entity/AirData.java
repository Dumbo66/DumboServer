package com.dumbo.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class AirData {
    @JsonIgnore
    private Integer dataId;

    private String siteId;

    private Date recordTime;

    private Float airTemperature;

    private Float airHumid;

    private Float lightIntensity;

    private Float pm10;

    private Float pm25;

    private Float voc;

    private Float co;

    private Float co2;

    private Float no2;

    private Float so2;

    private Float o3;

    private Float dustConcentration;

    private char grade;

    public char getGrade() {
        float standard=airTemperature;
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

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId == null ? null : siteId.trim();
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Float getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(Float airTemperature) {
        this.airTemperature = airTemperature;
    }

    public Float getAirHumid() {
        return airHumid;
    }

    public void setAirHumid(Float airHumid) {
        this.airHumid = airHumid;
    }

    public Float getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(Float lightIntensity) {
        this.lightIntensity = lightIntensity;
    }

    public Float getPm10() {
        return pm10;
    }

    public void setPm10(Float pm10) {
        this.pm10 = pm10;
    }

    public Float getPm25() {
        return pm25;
    }

    public void setPm25(Float pm25) {
        this.pm25 = pm25;
    }

    public Float getVoc() {
        return voc;
    }

    public void setVoc(Float voc) {
        this.voc = voc;
    }

    public Float getCo() {
        return co;
    }

    public void setCo(Float co) {
        this.co = co;
    }

    public Float getCo2() {
        return co2;
    }

    public void setCo2(Float co2) {
        this.co2 = co2;
    }

    public Float getNo2() {
        return no2;
    }

    public void setNo2(Float no2) {
        this.no2 = no2;
    }

    public Float getSo2() {
        return so2;
    }

    public void setSo2(Float so2) {
        this.so2 = so2;
    }

    public Float getO3() {
        return o3;
    }

    public void setO3(Float o3) {
        this.o3 = o3;
    }

    public Float getDustConcentration() {
        return dustConcentration;
    }

    public void setDustConcentration(Float dustConcentration) {
        this.dustConcentration = dustConcentration;
    }
}