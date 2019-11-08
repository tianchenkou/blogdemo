package me.koutian.controller;

import com.wf.captcha.SpecCaptcha;
import me.koutian.bean.User;
import me.koutian.service.TokenService;
import me.koutian.service.UserService;
import me.koutian.untils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: KouTian
 * @date: 2019-11-02 15:22
 * @description
 */
@Controller
public class CaptchaController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @ResponseBody
    @RequestMapping("/captcha")
    public Map<String, Object> captcha() throws Exception {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        // 存入redis并设置过期时间为5分钟
        redisUtil.setEx(key, verCode, 5, TimeUnit.MINUTES);

        HashMap<String, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("image",specCaptcha.toBase64());
        // 将key和base64返回给前端
        return AppResultBuilder.buildSuccessResult("ok","200",map);
    }

    @ResponseBody
    @PostMapping("/login")
    public Map<String, Object> login(String username, String password, @RequestParam("code") String verCode,@RequestParam("uuid") String verKey){
        // 获取redis中的验证码
        String redisCode = null;
        try{
            redisCode = redisUtil.get(verKey);
        }
        catch (Exception e){
            return AppResultBuilder.buildFailedResult("查询验证码失败","500");
        }
        // 判断验证码
        if (verCode==null ) {
            return AppResultBuilder.buildFailedResult("验证码已过期","444");
        }
        else if(!redisCode.equals(verCode.trim().toLowerCase())){
            return AppResultBuilder.buildFailedResult("验证码不正确","403");
        }

        //通过验证
        User user = userService.getUserByUsername(username, password);

        if (user == null) {
            return AppResultBuilder.buildFailedResult("用户名或者密码错误","403");
        }
        //登录成功，返回前端token
        String token = tokenService.getToken(user);
        System.out.println(token);

        HashMap<String, Object> map = new HashMap<>();
        map.put("token",token);
        //将密码置为null
        user.setPassword(null);
        map.put("user",user);

        return AppResultBuilder.buildSuccessResult("登录成功","200",map);
    }
}