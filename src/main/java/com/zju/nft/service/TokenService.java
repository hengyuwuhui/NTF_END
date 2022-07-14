package com.zju.nft.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zju.nft.entity.Response;
import com.zju.nft.entity.user.User;
import com.zju.nft.mapper.UserMapper;
import com.zju.nft.utils.Pair;
import com.zju.nft.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class TokenService {
    @Autowired
    private UserMapper userMapper;

    public String getToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() +  24 * 60 * 60 * 1000;//一天有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(user.getId().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    public Pair<User, Response> checkToken() {
        String token, userId;
        try {
            token = TokenUtil.getToken();
            userId = TokenUtil.getUserId();
        } catch (Exception e) {
            log.info("tokenError :" + e.getMessage());
            return new Pair<>(null, Response.tokenError);
        }

        if (userId == null) {
            return new Pair<>(null, Response.userNotLogin);
        }
        //System.out.println(userId);
        User user = userMapper.findUserById(Integer.parseInt(userId));

        if (user != null) {
            boolean verified = TokenUtil.verifyToken(token, user.getPassword(), user.getUsername());

            if (!verified) {
                log.info("tokenError : token验证失败");
                return new Pair<>(null, Response.tokenError);
            }

            return new Pair<>(user, null);
        } else {
            log.info("tokenError : 用户不存在");
            return new Pair<>(null, Response.tokenError);
        }
    }
}