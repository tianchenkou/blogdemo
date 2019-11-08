package me.koutian.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import me.koutian.bean.User;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: KouTian
 * @date: 2019-11-04 22:40
 * @description
 *          提供生成某个用户的token的方法
 */
@Service
public class TokenService {
    public String getToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(user.getUid().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
