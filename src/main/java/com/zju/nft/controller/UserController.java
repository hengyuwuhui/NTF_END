package com.zju.nft.controller;

import com.zju.nft.annotations.UserLoginToken;
import com.zju.nft.entity.Response;
import com.zju.nft.pojo.User.*;
import com.zju.nft.service.UserService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true", maxAge = 3600)
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/user/register")
    public Response register(@RequestBody RegisterPojo registerPojo){
        return userService.register(registerPojo);
    }

    @PostMapping("/user/login")
    public Response login(@RequestBody LoginPojo loginPojo){
        return userService.login(loginPojo);
    }

    @PostMapping("/user/updatePassword")
    @UserLoginToken
    public Response updatePassword(@RequestBody ChangePWPojo changePWPojo){
        return userService.updatePassword(changePWPojo);
    }

    @GetMapping("/liu_test1")
    @UserLoginToken
    public Response liu_test1(){

        return  userService.liu_test1();
    }

}
