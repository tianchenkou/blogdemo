package me.koutian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.koutian.service.TokenService;
import me.koutian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}