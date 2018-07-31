package com.dumbo.server.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * --监测点实体类--
 *
 * Created by Dumbo on 2018/4/22
 **/

public class Sites {
    @ApiModelProperty(value = "监测点号")
    private int site;

    @ApiModelProperty(value = "纬度（如39.933527）<bd09ll坐标系>")
    private double latBd09ll;

    @ApiModelProperty(value = "经度（如116.309408）<bd09ll坐标系>")
    private double lngBd09ll;

    @ApiModelProperty(value = "工作状态（“正常”/“故障”）")
    private String workState;

    @ApiModelProperty(value = "工作状态详细描述")
    private String description;

    public String getWorkState() {
        return workState;
    }

    public void setWorkState(String workState) {
        this.workState = workState;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSite() {
        return site;
    }

    public double getLatBd09ll() {
        return latBd09ll;
    }

    public double getLngBd09ll() {
        return lngBd09ll;
    }
}