package com.dumbo.server.service.serviceImpl;

import com.dumbo.server.constant.Common;
import com.dumbo.server.constant.SecretKey;
import com.dumbo.server.dao.UsersDao;
import com.dumbo.server.entity.Response;
import com.dumbo.server.entity.Users;
import com.dumbo.server.service.UsersService;
import com.dumbo.server.shiro.JwtFactory;
import com.dumbo.server.util.EncryptUtil;
import com.dumbo.server.util.ResponseUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dumbo on 2018/4/21
 **/

@Service
public class UsersServiceImpl implements UsersService{
    private final UsersDao usersDao;
    private Claims claims=null;
    private String path="";//头像存储url

    @Autowired
    public UsersServiceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    /*手机号密码注册*/
    @Override
    public Response registerByPasw(Map<String,Object> map, @RequestParam MultipartFile file){
        //自定义存储文件名
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String fileName = "img_" + sdf.format(date) + "_" + (int) (Math.random() * 10000) + ".jpg";

        if(path.equals("")){
            path=path+"/avatars/"+fileName;
        }else{
            path=path+"&/avatars/"+fileName;
        }
        System.out.println(path);

        //文件存放文件夹
        File photosFile = new File(Common.AVATAR_PHOTOS_PATH + File.separator + fileName);
        //判断文件父目录是否存在
        if (!photosFile.getParentFile().exists()) {
            photosFile.getParentFile().mkdirs();
        }

        //转存文件到指定路径
        try {
            file.transferTo(photosFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //手机号
        String phone= map.get("phone").toString();
        String password=map.get("password").toString();
        //RSA解密经RSA加密的密码
        String md5Pasw=EncryptUtil.decrypt(password);
        //加盐的MD5
        String salty2Md5Pasw=EncryptUtil.addSalt(md5Pasw);
        String nickName=map.get("nick_name").toString();
        String sex=map.get("sex").toString();

        Users users=new Users();
        users.setPhone(phone);
        users.setPassword(salty2Md5Pasw);
        users.setNickName(nickName);
        users.setSex(sex);
        users.setIconUrl(path);

        if(usersDao.selectByPhone(phone)==null){//未注册
            //插入user到数据库，注册成功
            usersDao.insert(users);
            path="";

            //查询user信息返回给客户端
            users=usersDao.selectByPhone(phone);
            int userId=users.getUserId();//获取数据库自增id
            String avatarUrl=users.getIconUrl();

            //生成accessToken和refreshToken
            String accessJwt=JwtFactory.createAccessJwt(userId+"","dumbo",
                    "general_user","all");
            String refreshJwt=JwtFactory.createRefreshJwt(userId+"");

            //将token和必要用户信息返回客户端
            Map<String,Object> responnseMap=new HashMap<>();
            responnseMap.put("access_jwt",accessJwt);
            responnseMap.put("refresh_jwt",refreshJwt);
            responnseMap.put("user_id",userId);
            responnseMap.put("avatar_url",avatarUrl);
            return ResponseUtil.ok("注册成功",responnseMap);
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

        if(usersDao.selectByPhone(loginPhone)==null){//未注册
            return ResponseUtil.unregistered();
        }else{//已注册
            //查询user信息
            users=usersDao.selectByPhone(loginPhone);
            int userId=users.getUserId();//获取数据库自增id
            String nickName=users.getNickName();
            String avatarUrl=users.getIconUrl();
            String salty2Md5Pasw= users.getPassword();
            String sex= users.getSex();

            if(EncryptUtil.verify(salty2Md5Pasw,md5Pasw)){//核对密码
                //生成accessToken和refreshToken，
                String accessJwt=JwtFactory.createAccessJwt(userId+"","dumbo",
                                                      "general_user","all");
                String refreshJwt=JwtFactory.createRefreshJwt(userId+"");

                //将token和必要用户信息返回客户端
                Map<String,Object> map=new HashMap<>();
                map.put("access_jwt",accessJwt);
                map.put("refresh_jwt",refreshJwt);
                map.put("user_id",userId);
                map.put("nick_name",nickName);
                map.put("avatar_url",avatarUrl);
                map.put("sex",sex);

                return ResponseUtil.ok("登录成功",map);
            }else{
                return ResponseUtil.phoneOrPaswIsErrored();//手机号或密码错误
            }
        }
    }

    /*手机号验证码登录*/
    @Override
    public Response loginByVercode(Users users){
        //手机号
        String loginPhone= users.getPhone();

        if(usersDao.selectByPhone(loginPhone)==null){//未注册
            return ResponseUtil.unregistered();
        }else{//已注册
            //查询user信息
            users=usersDao.selectByPhone(loginPhone);
            int userId=users.getUserId();//获取数据库自增id
            String nickName=users.getNickName();
            String avatarUrl=users.getIconUrl();
            String sex= users.getSex();

                //生成accessToken和refreshToken，
                String accessJwt=JwtFactory.createAccessJwt(userId+"","dumbo",
                        "general_user","all");
                String refreshJwt=JwtFactory.createRefreshJwt(userId+"");

                //将token和必要用户信息返回客户端
                Map<String,Object> map=new HashMap<>();
                map.put("access_jwt",accessJwt);
                map.put("refresh_jwt",refreshJwt);
                map.put("user_id",userId);
                map.put("nick_name",nickName);
                map.put("avatar_url",avatarUrl);
                map.put("sex",sex);

                return ResponseUtil.ok("登录成功",map);
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
            String newAccessJwt=JwtFactory.createAccessJwt(claims.getId(),"dumbo",
                                                    "general_user","all");
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
            String newAccessJwt=JwtFactory.createAccessJwt(claims.getId(),"dumbo",
                                                    "general_user","all");
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
