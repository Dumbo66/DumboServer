package com.dumbo.server.mqtt;

import com.dumbo.server.dao.SitesDao;
import com.dumbo.server.entity.Sites;
import com.dumbo.server.util.DataDecodeUtil;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dumbo on 2018/8/26
 **/

public class ReceiveMessageHandler implements MessageHandler {
    private String deviceId="";
    private int n=0;
    private double latSum=0;
    private double lngSum=0;

    @Autowired
    private SitesDao sitesDao;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        if(!message.equals("")){
            String json = (String) message.getPayload();
            deviceId=JsonPath.read(json,"$.id");
            double latSrc= Double.valueOf(JsonPath.read(json, "$.lat"));
            //将dddd.mmmm化为dd.mmmmmmmm...形势
            double lat=(int)(latSrc/100)+((latSrc/100-(int)(latSrc/100))*100)/60;
            double lngSrc= Double.valueOf(JsonPath.read(json, "$.lng"));
            //将dddd.mmmm化为dd.mmmmmmmm...形势
            double lng=(int)(lngSrc/100)+((lngSrc/100-(int)(lngSrc/100))*100)/60;
            String latType=JsonPath.read(json,"$.latType");
            String lngType=JsonPath.read(json,"$.lngType");
            String data=JsonPath.read(json,"$.data");

//            Map<String,Object> map=new HashMap<>();
//            map=DataDecodeUtil.decode(data);

            System.out.println("接收到消息：" + json);
            System.out.println("纬度：" + lat);
            System.out.println("经度：" + lng);

            n++;
            System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"+n);
            latSum+=lat;
            lngSum+=lng;
            if(n==30){
                double latAve = latSum / 30;
                double lngAve = lngSum / 30;

                System.out.println("纬度平均值："+ latAve);
                System.out.println("经度平均值："+ lngAve);

                Sites sites=new Sites();
                sites.setSiteId(deviceId);
                sites.setLatWgs84(latAve);
                sites.setLngWgs84(lngAve);
                sites.setLatType(latType);
                sites.setLngType(lngType);
                sites.setWorkState("正常");
                sites.setDescription("设备工作正常");

                //不存在则插入，否则更新
                if(sitesDao.selectByPrimaryKey(deviceId)==null){
                    sitesDao.insert(sites);
                }else{
                    sitesDao.updateByPrimaryKey(sites);
                }

                n=0;
                latSum=0;
                lngSum=0;
            }

//            System.out.println("接收到消息：" + deviceId);
//            System.out.println("接收到消息：" + latType);
//            System.out.println("接收到消息：" + lngType);
//            System.out.println("接收到消息：" + data);
        }
    }
}
