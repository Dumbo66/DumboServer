package com.dumbo.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * --定点监测数据实体类--
 *
 * Created by Dumbo on 2018/4/22
 **/

public class Data {
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    //记录时间
    private Date recordTime;
    //监测点号
    private int siteId;
    //PH
    private float ph;
    //温度
    private float temperature;
    //溶氧
    private float dissolvedOxygen;
    //电导率
    private float conductivity;
    //浊度
    private float turbidity;
    //水质等级
    private char grade;

    public Date getRecordTime() {
        return recordTime;
    }

    public int getSiteId() {
        return siteId;
    }

    public float getPh() {
        return ph;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getDissolvedOxygen() {
        return dissolvedOxygen;
    }

    public float getConductivity() {
        return conductivity;
    }

    public float getTurbidity() {
        return turbidity;
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