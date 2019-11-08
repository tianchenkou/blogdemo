package me.koutian.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: KouTian
 * @date: 2019-10-30 19:09
 * @description 用来提供返回json的数据格式
 * 一般是msg 、 code  提供两参构造器
 * 可能还需要数据，会加一个三参构造器
 */
public class AppResultBuilder {
    static Logger logger = LoggerFactory.getLogger(AppResultBuilder.class);
    static Map<String, Object> map;

    /**
     * 返回操作失败的信息
     *
     * @param msg  失败信息
     * @param code 失败码
     * @return
     */
    public static Map<String, Object> buildFailedResult(String msg, String code) {
        initMap(msg, code);
        logger.warn(msg);
        return map;
    }

    /**
     * 返回操作成功的信息
     *
     * @param msg  成功信息
     * @param code 成功码
     * @return
     */
    public static Map<String, Object> buildSuccessResult(String msg, String code) {
        initMap(msg, code);
        return map;
    }

    /**
     * 返回操作成功的信息
     *
     * @param data 返回给前端的数据
     * @param msg  成功信息
     * @param code 成功码
     * @return
     */
    public static Map<String, Object> buildSuccessResult(String msg, String code, Object data) {
        initMap(msg, code, data);
        return map;
    }

    /**
     * 处理所有返回信息的初始化类
     *
     * @param args 需要返回的的参数
     */
    private static void initMap(Object... args) {
        map = new HashMap<>();
        try {
            map.put("msg", args[0]);
            map.put("code", args[1]);
            if (args.length > 2) {
                map.put("data", args[2]);
            }
        } catch (Exception e) {
            map.put("msg", "内部错误");
            map.put("code", "500");
            logger.error("传参错误，请按照要求使用AppResultBuilder类");
        }
    }
}
