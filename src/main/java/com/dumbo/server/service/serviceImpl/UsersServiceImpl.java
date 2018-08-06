package com.dumbo.server.service.serviceImpl;

import com.dumbo.server.constant.SecretKey;
import com.dumbo.server.dao.UsersDao;
import com.dumbo.server.entity.Response;
import com.dumbo.server.entity.Users;
import com.dumbo.server.service.UsersService;
import com.dumbo.server.shiro.JwtFactory;
import com.dumbo.server.util.EncryptUtil;
import com.dumbo.server.util.EncryptUtil;
import com.dumbo.server.util.ResponseUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dumbo on 2018/4/21
 **/

@Service
public class UsersServiceImpl implements UsersService{
    private final UsersDao usersDao;
    private Claims claims=null;

    @Autowired
    public UsersServiceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    /*手机号密码注册*/
    @Override
    public Response registerByPasw(Users users){
        //手机号
        String phone= users.getPhone();
        //RSA加密的密码
        String Base64RsaEncryptedPasw= users.getPassword();

        //RSA解密
        String md5Pasw=EncryptUtil.decrypt(Base64RsaEncryptedPasw);
        System.out.println("解密后:"+md5Pasw);

        //加盐
        String salty2Md5Pasw=EncryptUtil.addSalt(md5Pasw);
        System.out.println("加盐后:"+salty2Md5Pasw);

        //存储加盐的MD5
        users.setPassword(salty2Md5Pasw);

        if(usersDao.selectByPhone(phone)==null){//未注册
            //插入user到数据库，注册成功
            usersDao.insert(users);
            //生成JWT并返回给客户端
            int userId=usersDao.selectByPhone(phone).getId();//获取数据库自增id
            String jwt=JwtFactory.createAccessJwt(userId+"","dumbo","general_user","all");
            return ResponseUtil.ok("注册成功",jwt);
        }else{
            return ResponseUtil.registered();//已注册
        }
    }

    /*删除某用户所有信息*/
    @Override
    public Response deleteUser(String phone) {
        usersDao.deleteByPhone(phone);
        return ResponseUtil.ok("删除用户所有信息成功");
    }

    /*修改某用户信息*/
    @Override
    public Response updateUser(Users users) {
        usersDao.update(users);
        return ResponseUtil.ok("修改用户信息成功");
    }

    /*查询某用户信息*/
    @Override
    public Response selectUser(String phone) {
        Users users =usersDao.selectByPhone(phone);
        if(users ==null){
            return ResponseUtil.unregistered();
        }else{
            return ResponseUtil.registered();
        }
    }

    /*手机号密码登录*/
    @Override
    public Response loginByPasw(Users users){
        //手机号
        String loginPhone= users.getPhone();
        //RSA加密的密码
        String RsaEncryptedPasw= users.getPassword();

        //RSA解密
        String md5Pasw=EncryptUtil.decrypt(RsaEncryptedPasw);
        System.out.println("解密后:"+md5Pasw);

        if(usersDao.selectByPhone(loginPhone)==null){//未注册
            return ResponseUtil.unregistered();
        }else{//已注册
            String salty2Md5Pasw= usersDao.selectByPhone(loginPhone).getPassword();
            if(EncryptUtil.verify(salty2Md5Pasw,md5Pasw)){//核对密码
                //生成accessToken和refreshToken并返回给客户端
                int userId=usersDao.selectByPhone(loginPhone).getId();//获取数据库自增id
                String accessJwt=JwtFactory.createAccessJwt(userId+"","dumbo","general_user","all");
                String refreshJwt=JwtFactory.createRefreshJwt(userId+"");
                System.out.println("refreshToken="+refreshJwt);
                Map<String,Object> map=new HashMap<>();
                map.put("accessJwt",accessJwt);
                map.put("refreshJwt",refreshJwt);
                return ResponseUtil.ok("登录成功",map);
            }else{
                return ResponseUtil.phoneOrPaswIsErrored();//手机号或密码错误
            }
        }
    }

    /*手机号验证码登录*/
    @Override
    public Response loginByVercode(Users users){
        String loginPhone= users.getPhone();
        if(usersDao.selectByPhone(loginPhone)==null){
            usersDao.insert(users);
            return ResponseUtil.ok("登录成功");
        }else{
            return ResponseUtil.ok("登录成功");
        }
    }

    @Override
    /*刷新accessJwt*/
    public Response verifyAccessJwt(String accessJwt) {
        try {//签名校验成功&未过期
            Claims claims=Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SecretKey.JWT_SECRET_KEY))
                    .parseClaimsJws(accessJwt).getBody();
            //生成JWT并返回给客户端
            String newAccessJwt=JwtFactory.createAccessJwt(claims.getId(),"dumbo","general_user","all");
            Map<String,Object> map=new HashMap<>();
            map.put("accessJwt",newAccessJwt);
            map.put("refreshJwt",null);
            System.out.println("签名校验成功");
            return ResponseUtil.ok("签名校验成功",map);
        } catch (SignatureException e) {//签名校验失败
            System.out.println("签名校验失败");
            return ResponseUtil.tokenIsInvalid();
        } catch (ExpiredJwtException e){//已过期
            System.out.println("AccessToken已过期,重新获取token");
            return ResponseUtil.tokenIsExpired();
        }
    }

    /*刷新refreshJwt*/
    public Response verifyRefreshJwt(String refreshJwt) {
        try {//签名校验成功&未过期
            Claims claims=Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SecretKey.JWT_SECRET_KEY))
                    .parseClaimsJws(refreshJwt).getBody();

            //生成accessToken和refreshToken并返回给客户端
            String newAccessJwt=JwtFactory.createAccessJwt(claims.getId(),"dumbo","general_user","all");
            String newRefreshJwt=JwtFactory.createRefreshJwt(claims.getId());
            System.out.println(newAccessJwt);
            System.out.println(newRefreshJwt);
            Map<String,Object> map=new HashMap<>();
            map.put("accessJwt",newAccessJwt);
            map.put("refreshJwt",newRefreshJwt);
            System.out.println("签名校验成功");
            return ResponseUtil.ok("签名校验成功",map);
        } catch (SignatureException e) {//签名校验失败
            System.out.println("签名校验失败");
            return ResponseUtil.tokenIsInvalid();
        } catch (ExpiredJwtException e){//已过期
            System.out.println("RefreshToken已过期,重新登录");
            return ResponseUtil.loginAgain();
        }
    }
}
