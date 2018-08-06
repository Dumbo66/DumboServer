package com.dumbo.server.util;

import com.dumbo.server.constant.SecretKey;
import org.apache.shiro.codec.Hex;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;
import java.util.Random;

/**
 * --加密工具类--
 *
 * Created by dumbo on 2018/5/19
 **/

public class EncryptUtil {

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

    /**
     * 加密
     * @param srcData 待加密的明文数据
     * @return 加密后的数据String
     */
    public static String encrypt(String srcData){
        String enryptedStr=null;

        //将字符串形式的公钥转换为公钥对象
        PublicKey publicKey = null;
        byte[] keyBytes = Base64.decode(SecretKey.RSA_PUBLIC_KEY);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        //加密
        try {
            //获取Cipher实例，
            Cipher cipher = Cipher.getInstance("RSA",
                    new org.bouncycastle.jce.provider.BouncyCastleProvider());
            //初始化Cipher，mode指定是加密还是解密，key为公钥或私钥
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            //处理数据
            byte[] bytes=cipher.doFinal(srcData.getBytes());
            enryptedStr=Base64.toBase64String(bytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException
                | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return enryptedStr;
    }

    /**
     * 解密
     * @param Base64RsaEncryptedData 以Base64编码的经过RSA加密的数据
     * @return 解密后的明文String
     */
    public static String decrypt(String Base64RsaEncryptedData){
        String decryptedData=null;

        //将字符串形式的私钥转换为私钥对象
        PrivateKey privateKey = null;
        byte[] keyBytes = Base64.decode(SecretKey.RSA_PRIVATE_KEY);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        //Base64解码得到RSA加密的数据bytes
        byte[] rsaEncrypteddataBytes=Base64.decode(Base64RsaEncryptedData.getBytes());

        //解密
        try {
            //获取Cipher实例
            Cipher cipher = Cipher.getInstance("RSA",
                    new org.bouncycastle.jce.provider.BouncyCastleProvider());
            //初始化Cipher，mode指定是加密还是解密，key为公钥或私钥
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            //处理数据
            if (rsaEncrypteddataBytes != null) {
                byte[] bytes = cipher.doFinal(rsaEncrypteddataBytes);
                decryptedData=new String(bytes,"UTF-8");
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException
                | IllegalBlockSizeException | InvalidKeyException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decryptedData;
    }
}

