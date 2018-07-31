package com.dumbo.server.util;


import com.dumbo.server.constant.SecretKey;
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

/**
 * --RSA加密类--
 *
 * Created by Dumbo on 2018/5/18
 **/

public class RSAUtil {
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