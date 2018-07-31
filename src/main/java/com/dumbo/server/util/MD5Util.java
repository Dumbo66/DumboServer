package com.dumbo.server.util;

import org.apache.shiro.codec.Hex;

import java.security.MessageDigest;
import java.util.Objects;
import java.util.Random;

/**
 * --MD5加密工具类+加盐--
 *
 * Created by dumbo on 2018/5/19
 **/

public class MD5Util {

    /**
     * MD5加密方法
     * @param s 待加密字符串
     * @return MD5密文
     */
    public static String getMD5Str(String s) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(s.getBytes());
            return new String(Hex.encode(bytes)).toUpperCase();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * MD5加盐
     * @param md5Pasw MD5加密的密码
     * @return 加盐并插入盐的MD5
     */
    public static String addSalt(String md5Pasw){
        Random random=new Random();
        StringBuilder sb=new StringBuilder(16);
        sb.append(random.nextInt(99999999)).append(99999999);
        if(sb.length()<16){
            for(int i=0;i<16-sb.length();i++){
                sb.append(0);
            }
        }
        String salt=sb.toString();
        String saltyMd5Pasw=getMD5Str(md5Pasw+salt);//加盐的MD5
        StringBuilder salty2Md5Pasw= new StringBuilder();//加盐并加入盐的MD5
        String[] s1=new String[7];
        String[] s2=new String[7];
        int m=0,n=0;
        for(int i=0;i<7;i++){
            if(i<4){
                s1[i]=Objects.requireNonNull(saltyMd5Pasw).substring(n,n+=2*(i+1));
                s2[i]=salt.substring(m,m+=(i+1));
            }else{
                s1[i]=saltyMd5Pasw.substring(n,n+=2*(8-(i+1)));
                s2[i]=salt.substring(m,m+=(8-(i+1)));
            }
            salty2Md5Pasw.append(s1[i]).append(s2[i]);
        }
        return salty2Md5Pasw.toString();
    }

    /**
     * 校验加盐后是否和原文一致
     * @param md5Pasw 普通MD5
     * @param salty2Md5Pasw 加盐并插入盐的MD5
     * @return 是否相等
     */
    public static boolean verify( String salty2Md5Pasw,String md5Pasw) {
        String[] s=new String[7];
        String[] md5=new String[7];
        String[] salt=new String[7];
        StringBuilder md5Str = new StringBuilder();
        StringBuilder saltStr=new StringBuilder();
        int n=0;
        for(int i=0;i<7;i++){
            if(i<4){
                s[i]=salty2Md5Pasw.substring(n,n+=3*(i+1));
                md5[i]=s[i].substring(0,2*(i+1));
                salt[i]=s[i].substring(2*(i+1),s[i].length());
            }else{
                s[i]=salty2Md5Pasw.substring(n,n+=3*(8-(i+1)));
                md5[i]=s[i].substring(0,2*(8-(i+1)));
                salt[i]=s[i].substring(2*(8-(i+1)),s[i].length());
            }
            md5Str.append(md5[i]);
            saltStr.append(salt[i]);
        }
        return Objects.equals(getMD5Str(md5Pasw + saltStr.toString()), md5Str.toString());
    }
}

