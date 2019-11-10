package me.koutian.service;

import me.koutian.bean.User;
import me.koutian.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author: KouTian
 * @date: 2019-11-04 20:25
 * @description
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User getUserByUsername(String username, String password) {
        //TODO 这里可以考虑做MD5加密，甚至加盐
        HashMap<String, String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        return  userMapper.findUserByName(map);
    }


    public User findUserById(String userId) {
        return userMapper.findUserById(Integer.valueOf(userId));
    }

    public void setAvatarByUid(Integer uid, String url) {
        User user = new User();
        user.setUid(uid.toString());
        user.setAvatar(url);
        userMapper.updateUser(user);
    }
}
