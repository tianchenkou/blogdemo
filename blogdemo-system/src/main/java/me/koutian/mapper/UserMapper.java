package me.koutian.mapper;

import me.koutian.bean.User;

import java.util.Map;

public interface UserMapper {
    User findUserById(int userId);

    /**
     *             该方法主要是用于登录，去数据库查找相应的用户，并返回
     * @param map
     * @return
     */
    User findUserByName(Map map);
}
