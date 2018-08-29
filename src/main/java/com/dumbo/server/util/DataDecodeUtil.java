package com.dumbo.server.util;

import java.util.HashMap;
import java.util.Map;

public class DataDecodeUtil {
	/**监测数据解析*/
	public static Map<String,Object> decode(String data){
		Map<String,Object> map=new HashMap<>();
		String headStr=data.substring(0,6);//头部信息
		String waterDataStr=data.substring(6,46);//水质监测数据
		String airDataStr=data.substring(46,86);//空气质量监测数据
		String latLngStr=data.substring(86,110);//经纬度
		String timeStr=data.substring(110,126);//时间
		String crcStr=data.substring(126,130);//时间

		System.out.println("--------------------");
		System.out.println("headStr="+headStr);
		System.out.println("waterDataStr="+waterDataStr);
		System.out.println("airDataStr="+airDataStr);
		System.out.println("latLngStr="+latLngStr);
		System.out.println("timeStr="+timeStr);
		System.out.println("crcStr="+crcStr);

		//****************节点号解析*******************
		String siteIdStr=headStr.substring(0,2);
		int siteId=Integer.valueOf(siteIdStr,16);

		System.out.println("----------节点号解析----------");
		System.out.println("siteId="+siteId);

		//****************水质监测数据解析******************
		String temStr=waterDataStr.substring(0,4);//温度Str
		String phStr=waterDataStr.substring(4,8);//PH Str
		String dioStr=waterDataStr.substring(8,12);//溶氧Str
		String turStr=waterDataStr.substring(12,16);//浊度Str
		String conStr=waterDataStr.substring(16,20);//电导率Str
		String conJStr=waterDataStr.substring(20,24);//电导率Str

		float tem=(float) (decodeHex(temStr)/100.0);//温度
		float ph=(float) (decodeHex(phStr)/100.0);//PH
		float dio=(float) (decodeHex(dioStr)/100.0);//溶氧
		float tur=(float) (decodeHex(turStr)/10.0);//浊度
		float con=0;
		switch (conJStr) {
			case "0004":
				con = (float) (decodeHex(conStr)*10);//电导率
				break;

			case "0003":
				con = (float) (decodeHex(conStr)*1);//电导率
				break;

			case "0002":
				con = (float) (decodeHex(conStr)/10);//电导率
				break;

			case "0001":
				con = (float) (decodeHex(conStr)/100);//电导率
				break;
		}

		System.out.println("---------水质监测数据解析-----------");
		System.out.println("tem="+tem);
		System.out.println("ph="+ph);
		System.out.println("dio="+dio);
		System.out.println("tur="+tur);
		System.out.println("con="+con);

		//****************空气质量监测数据解析******************
		String VOCStr=airDataStr.substring(0,4);//VOCStr
		String CO2Str=airDataStr.substring(4,8);//CO2Str
		String SO2Str=airDataStr.substring(8,12);//SO2Str
		String NO2Str=airDataStr.substring(12,16);//NO2Str
		String COStr=airDataStr.substring(16,20);//COStr
		String O3Str=airDataStr.substring(20,24);//O3CStr
		String PM25Str=airDataStr.substring(24,28);//PM25Str
		String PM10VOCStr=airDataStr.substring(28,32);//PM10Str
		String airTemStr=airDataStr.substring(32,36);//airTemStr
		String airHumidStr=airDataStr.substring(36,40);//airHumidStr

		//******************经纬度解析********************
		String latStr=latLngStr.substring(0,12);
		String lngStr=latLngStr.substring(12,24);

		//******************纬度解析********************
		String weStr=latStr.substring(0,2);//东西半球Str
		String lngDStr=latStr.substring(2,4);//度Str
		String lngMStr=latStr.substring(4,6);//分Str
		String lngSStr=latStr.substring(6,8);//秒Str
		String lngMSStr=latStr.substring(8,12);//毫秒Str

		char we = 0;//东西半球
		if(weStr.equals("45")){
			we='E';
		}else if(weStr.equals("57")){
			we='W';
		}
		int lngD=Integer.valueOf(lngDStr,16);//度
		int lngM=Integer.valueOf(lngMStr,16);//分
		int lngS=Integer.valueOf(lngSStr,16);//秒
		int lngMS=Integer.valueOf(lngMSStr,16);//毫秒

		System.out.println("----------纬度解析----------");
		System.out.println("we="+we);
		System.out.println("lngD="+lngD);
		System.out.println("lngM="+lngM);
		System.out.println("lngS="+lngS);
		System.out.println("lngMS="+lngMS);

		//******************经度解析********************
		String snStr=lngStr.substring(0,2);//南北半球Str
		String latDStr=lngStr.substring(2,4);//度Str
		String latMStr=lngStr.substring(4,6);//分Str
		String latSStr=lngStr.substring(6,8);//秒Str
		String latMSStr=lngStr.substring(8,12);//毫秒Str

		char sn = 0;//南北半球
		if(snStr.equals("4E")){
			sn='N';
		}else if(snStr.equals("53")){
			sn='S';
		}
		int latD=Integer.valueOf(latDStr,16);//度
		int latM=Integer.valueOf(latMStr,16);//分
		int latS=Integer.valueOf(latSStr,16);//秒
		int latMS=Integer.valueOf(latMSStr,16);//毫秒

		System.out.println("----------经度解析---------");
		System.out.println("sn="+sn);
		System.out.println("latD="+latD);
		System.out.println("latM="+latM);
		System.out.println("latS="+latS);
		System.out.println("latMS="+latMS);

		//******************时间解析********************
		String timeYStr=timeStr.substring(0,2);//年Str
		String timeMStr=timeStr.substring(2,4);//月Str
		String timeDStr=timeStr.substring(4,6);//日Str
		String timeHStr=timeStr.substring(6,8);//时Str
		String timemStr=timeStr.substring(8,10);//分Str
		String timeSStr=timeStr.substring(10,12);//秒Str

		int timeY=20*100+Integer.valueOf(timeYStr,16);//年
		int timeM=Integer.valueOf(timeMStr,16);//月
		int timeD=Integer.valueOf(timeDStr,16);//日
		int timeH=Integer.valueOf(timeHStr,16);//时
		int timem=Integer.valueOf(timemStr,16);//分
		int timeS=Integer.valueOf(timeSStr,16);//秒
		String time=timeY+"-"+timeM+"-"+timeD+" "+timeH+":"+timem+":"+timeS;

//		System.out.println("time="+time);
//		Date date=new Date();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		sdf.format(date);

		System.out.println("lllllllllllllllllllllllllllllllllllllllllllll");
		double latMS1=latS+latMS/1000.0;
		System.out.println("latMS1="+latMS1);
		double latS1=latS+latMS1/60.0;
		System.out.println("latS1="+latS1);
		double latWGS84=latD+latS1/60.0;
		if(sn=='S'){
			latWGS84=-latWGS84;
		}
		System.out.println("latWGS84="+latWGS84);

		System.out.println("lllllllllllllllllllllllllllllllllllllllllllll");
		double lngMS1=lngS+lngMS/1000.0;
		System.out.println("lngMS1="+lngMS1);
		double lngS1=latS+lngMS1/60.0;
		System.out.println("lngS1="+lngS1);
		double lngWGS84=lngD+lngS1/60.0;
		if(we=='W'){
			lngWGS84=-lngWGS84;
		}
		System.out.println("lngWGS84="+lngWGS84);

		map.put("siteId",siteId);
		map.put("tem",tem);
		map.put("dio",dio);
		map.put("tur",tur);
		map.put("ph",ph);
		map.put("con",con);

		return map;
	}

	/*将4位十六进制分为高八位和低八位分别转为十进制i1和i2后，返回i1*100+i2*/
	private static int decodeHex(String hex){
		String tem1=hex.substring(0,2);
		String tem2=hex.substring(2,4);
		int i1=Integer.valueOf(tem1,16);
		int i2=Integer.valueOf(tem2,16);
		return i1*100+i2;
	}
}
