package com.dumbo.server.mqtt;

import com.dumbo.server.dao.AirDataDao;
import com.dumbo.server.dao.SitesDao;
import com.dumbo.server.dao.WaterDataDao;
import com.dumbo.server.entity.AirData;
import com.dumbo.server.entity.Sites;
import com.dumbo.server.entity.WaterData;
import com.dumbo.server.util.DataDecodeUtil;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dumbo on 2018/8/26
 **/

public class ReceiveMessageHandler implements MessageHandler {
    private static final int N=30;//经纬度计数30次取平均值
    private int n=0;//经纬度计数
    private static final int M=6;//监测数据计数6次取平均值
    private int m=0;//监测数据计数

    private double latSum=0;
    private double lngSum=0;

    private double waterTemSum=0;
    private double phSum=0;
    private double dioSum=0;
    private double conSum=0;
    private double turSum=0;

    private double VOCSum=0;
    private double CO2Sum=0;
    private double SO2Sum=0;
    private double NO2Sum=0;
    private double COSum=0;
    private double O3Sum=0;
    private double PM25Sum=0;
    private double PM10Sum=0;
    private double airTemSum=0;
    private double airHumidSum=0;

    @Autowired
    private SitesDao sitesDao;

    @Autowired
    private WaterDataDao waterDataDao;

    @Autowired
    private AirDataDao airDataDao;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        if(!message.equals("")){
            String json = (String) message.getPayload();
            String deviceId = JsonPath.read(json, "$.id");

            double latSrc= Double.valueOf(JsonPath.read(json, "$.lat"));
            //将dddd.mmmm化为dd.mmmmmmmm...形式
            double lat=(int)(latSrc/100)+((latSrc/100-(int)(latSrc/100))*100)/60;
            double lngSrc= Double.valueOf(JsonPath.read(json, "$.lng"));
            //将dddd.mmmm化为dd.mmmmmmmm...形式
            double lng=(int)(lngSrc/100)+((lngSrc/100-(int)(lngSrc/100))*100)/60;
            String latType=JsonPath.read(json,"$.latType");
            String lngType=JsonPath.read(json,"$.lngType");

            String data=JsonPath.read(json,"$.data");

            Map<String,Object> map=new HashMap<>();
            map=DataDecodeUtil.decode(data);

            //监测点号=主节点号设备号+节点编号（字符串相连）
            String siteId=deviceId +map.get("point_id");
            System.out.println("siteId="+siteId);

            System.out.println("接收到消息：" + json);
            System.out.println("纬度：" + lat);
            System.out.println("经度：" + lng);

            //经纬度：计数满30次后求平均值存入数据库中（若10s接收一次，则5min更新一次数据）
            n++;
            System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"+n);
            latSum+=lat;
            lngSum+=lng;
            System.out.println(n==N);
            if(n==N){
                Sites sites=new Sites();
                sites.setSiteId(siteId);
                sites.setLatWgs84(latSum / N);
                sites.setLngWgs84(lngSum / N);
                sites.setLatType(latType);
                sites.setLngType(lngType);
                sites.setWorkState("正常");
                sites.setDescription("设备工作正常");

                System.out.println("纬度平均值："+ sites.getLatWgs84());
                System.out.println("经度平均值："+ sites.getLngWgs84());

                //不存在则插入，否则更新
                if(sitesDao.selectByPrimaryKey(siteId)==null){
                    sitesDao.insert(sites);
                }else{
                    sitesDao.updateByPrimaryKey(sites);
                }

                n=0;
                latSum=0;
                lngSum=0;
            }

            //监测数据：计数满6次后求平均值存入数据库中（若10s接收一次，则1min更新一次数据）
            m++;
            waterTemSum+=(float)map.get("water_tem");
            dioSum+=(float)map.get("dio");
            phSum+=(float)map.get("ph");
            conSum+=(float)map.get("con");
            turSum+=(float)map.get("tur");
            System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"+m);
            VOCSum+=(float)map.get("VOC");
            CO2Sum+=(float)map.get("CO2");
            SO2Sum+=(float)map.get("SO2");
            NO2Sum+=(float)map.get("NO2");
            COSum+=(float)map.get("CO");
            O3Sum+=(float)map.get("O3");
            PM25Sum+=(float)map.get("PM25");
            PM10Sum+=(float)map.get("PM10");
            airTemSum+=(float)map.get("air_tem");
            airHumidSum+=(float)map.get("air_humid");
            if(m==M){
                WaterData waterData=new WaterData();
                waterData.setSiteId(siteId);
                waterData.setRecordTime(new Date());
                waterData.setPh((float) (phSum/M));
                waterData.setTurbidity((float) (turSum/M));
                waterData.setConductivity((float) (conSum/M));
                waterData.setDissolvedOxygen((float) (dioSum/M));
                waterData.setTemperature((float) (waterTemSum/M));
                waterDataDao.insert(waterData);

                AirData airData=new AirData();
                airData.setSiteId(siteId);
                airData.setRecordTime(new Date());
                airData.setAirHumid((float) (airHumidSum/M));
                airData.setAirTemperature((float) airTemSum/M);
                airData.setCo((float) COSum/M);
                airData.setCo2((float) CO2Sum/M);
                airData.setSo2((float) SO2Sum/M);
                airData.setNo2((float) NO2Sum/M);
                airData.setVoc((float) VOCSum/M);
                airData.setO3((float) O3Sum/M);
                airData.setPm25((float) PM25Sum/M);
                airData.setPm10((float) PM10Sum/M);
                airDataDao.insert(airData);

                m=0;
                waterTemSum=0;
                dioSum=0;
                phSum=0;
                turSum=0;
                conSum=0;

                airHumidSum=0;
                airTemSum=0;
                COSum=0;
                CO2Sum=0;
                NO2Sum=0;
                SO2Sum=0;
                PM25Sum=0;
                PM10Sum=0;
                VOCSum=0;
                O3Sum=0;
            }
        }
    }
}
