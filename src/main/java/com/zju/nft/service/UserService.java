package com.zju.nft.service;

import com.zju.nft.entity.Response;
import com.zju.nft.entity.user.*;
import com.zju.nft.mapper.UserMapper;
import com.zju.nft.pojo.User.ChangePWPojo;
import com.zju.nft.pojo.User.*;
import com.zju.nft.utils.Pair;
import com.zju.nft.utils.PasswordUtil;
import com.zju.nft.utils.StringUtils;
import com.zju.nft.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@Service
public class UserService {

    @Autowired
    public UserMapper userMapper;

    @Resource
    private TokenService tokenService;


    public User findById(int id){
        return userMapper.findUserById(id);
    }   //拦截器使用,主要是为了统一

    public Response login(LoginPojo loginPojo){
        User user = userMapper.findByUsername(loginPojo.getUsername());

        if (user == null){
            return Response.fail(1001, "用户名不存在", null);
        }

        String passwordHashHex = PasswordUtil.passwordHash(loginPojo.getPassword(), user.getSalt());

        if (!user.getPassword().equals(passwordHashHex)){
            return Response.fail(1002, "密码错误", null);
        }

        String token = tokenService.getToken(user);
        Map<String, String> resp = new HashMap<>();
        resp.put("token", token);

        return Response.success(resp);
    }

    public Response register(RegisterPojo registerPojo){
        if (registerPojo.getUsername() == null || registerPojo.getPassword() == null){
            return Response.fail(1003, "必须填写用户名和密码", null);
        }
        User user = userMapper.findByUsername(registerPojo.getUsername());
        if (user != null){
            return Response.fail(1004, "用户名已存在", null);
        }

        String salt = PasswordUtil.getRandSalt();
        String passwordHashHex = PasswordUtil.passwordHash(registerPojo.getPassword(), salt);

        Integer sum = userMapper.sumUser();

        userMapper.registerUser(sum + 1, registerPojo.getUsername(), passwordHashHex, salt);

        Map<String, String> resp = new HashMap<>();
        resp.put("username", registerPojo.getUsername());

        return Response.success(resp);
    }

    public Response updatePassword(ChangePWPojo changePWPojo){
        Pair<User, Response> pair = tokenService.checkToken();
        Response check = pair.getValue();
        if (check != null){
            log.info("token error: " + TokenUtil.getTokenContent());
            log.info("msg: " + check.getMessage());
            return Response.fail(1000,"用户验证不通过",null);
        }

        User user = pair.getKey();

        String pw = StringUtils.getTrimString(changePWPojo.getNewpassword());
        if (pw == null){
            String message = "pw为空";
            log.info(message);
            return Response.fail(1005, message, null);
        }

        String passwordHashHex = PasswordUtil.passwordHash(changePWPojo.getOldpassword(), user.getSalt());

        if (!user.getPassword().equals(passwordHashHex)){
            return Response.fail(1006, "旧密码错误", null);
        }

        String newPW = PasswordUtil.passwordHash(pw, user.getSalt());

        userMapper.updatePassword(newPW, user.getSalt(), user.getId());

        String successMessage = String.format("userId(%s)修改密码成功", user.getId());
        log.info(successMessage);
        return Response.success(successMessage, null);
    }

    public Response liu_test1(){
        System.out.println("hello world");

        String successMessage = String.format("123");
        log.info(successMessage);
        return Response.success(successMessage, null);
    }

}
