package me.koutian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.koutian.annotation.UserLoginToken;
import me.koutian.bean.User;
import me.koutian.service.TokenService;
import me.koutian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: KouTian
 * @date: 2019-11-04 22:43
 * @description
 */
@RestController
@RequestMapping("/user")
@Api(value = "/user",description = "一个用来测试swagger注解的控制器")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    /*测试token  不登录没有token*/
//    @UserLoginToken
    @GetMapping("/getMessage")
    @ApiOperation(value="测试token是否起作用", notes="test:token")
    public String getMessage(@RequestParam Integer userNumber) {
        return "你已通过验证";
    }

    @UserLoginToken
    @ApiOperation(value="对用户头像进行更改", notes="user:Avatar")
    @PostMapping("upadateAvatar")
    public Map<String,Object> setAvatar(@RequestBody User user){
        String url = null;
        Integer uid = null;
        try {
            url = user.getAvatar();
            uid = Integer.valueOf(user.getUid());
        }catch (Exception e){
            return AppResultBuilder.buildFailedResult("error","500");
        }
        if (url==null || "".equals(url) || uid == null){
            //参数为空
            return AppResultBuilder.buildFailedResult("必须参数为空","404");
        }
        try {
            userService.setAvatarByUid(uid,url);
        }catch (Exception e){
            return AppResultBuilder.buildFailedResult("error","500");
        }
        return AppResultBuilder.buildSuccessResult("更改头像成功","200");
    }
}