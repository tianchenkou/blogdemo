package me.koutian.controller;

import me.koutian.annotation.UserLoginToken;
import me.koutian.bean.Blog;
import me.koutian.mapper.BlogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: KouTian
 * @date: 2019-10-21 20:24
 * @description
 */
@UserLoginToken
@RestController
public class BlogController {
    //    private static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);
    @Autowired
    BlogMapper blogMapper;

    Logger logger = LoggerFactory.getLogger(getClass());

    @UserLoginToken
    @GetMapping("/getBlogs/{id:\\d+}")
    public Map<String, Object> getBlogsByCateDeId(@PathVariable("id") Integer id) {
        Map<String, Object> map = null;
        List<Blog> blogList = null;

        //交给前台验证的状态码
        int code = 200;
        String msg = "查询成功";

        //参数处理
        if (id == null) {
            //springBoot一般不需要参数即为地址
            code = 501;
            msg = "错误查询，分类id为空";
        } else {
            //异常处理
            try {
                blogList = blogMapper.getBlogsByCateDeId(id);

                //判断链表是否为空
                if (blogList == null || blogList.size() == 0) {
                    msg = "这个分类的博客信息为空";
                }

            } catch (Exception e) {
                code = 500;
                msg = "查询失败，发生异常";
            }
        }
        map = new HashMap<>();

        map.put("code", code);
        map.put("msg", msg);
        map.put("info", blogList);

        return map;
    }
    @UserLoginToken
    @PostMapping(value = "/saveArticle", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String, Object> upload(@RequestBody Blog blog) {
        Map<String, Object> map = null;
        int code = 200;
        String msg = "保存成功";

//        System.out.println(blog.getTitle());
//        System.out.println(blog.getContent());
//        System.out.println(blog.getAuthor());
//        System.out.println(blog.getDate());
//        System.out.println(blog.getCatedeId());
        if (blog.getAuthor() == null || blog.getDate() == null || blog.getTitle() == null || blog.getCatedeId() == null || blog.getContent() == null || blog.getContent().equals("")) {
            code = 503;
            msg = "保存失败，必选项为空";
            logger.warn("保存失败，必选项为空");
        } else {//可以正常保存
            try {
                blogMapper.saveBlog(blog);
            } catch (Exception e) {
                code = 500;
                msg = "保存失败，服务器异常";
                logger.error("保存失败，服务器异常");
            }
        }
        map = new HashMap<>();

        map.put("code", code);
        map.put("msg", msg);

        return map;
    }
    @UserLoginToken
    @GetMapping("/getBlog/{id:\\d+}")//表示这个映射器只会匹配第二个参数为数字的请求
    public Map<String, Object> getBlogByDeId(@PathVariable("id") Integer id) {
        Map<String, Object> map = null;
        Blog blog = null;

        //交给前台验证的状态码
        int code = 200;
        String msg = "查询成功";

        //参数处理
        if (id == null) {
            //springBoot一般不需要参数即为地址
            code = 501;
            msg = "错误查询，博客id为空";
            logger.error("错误查询，博客id为空");
        } else {
            //异常处理
            try {
                blog = blogMapper.getBlogById(id);

                //判断链表是否为空
                if (blog == null) {
                    code = 404;
                    msg = "这个博客信息为空";
                    logger.warn("这个博客信息为空");
                }

            } catch (Exception e) {
                code = 500;
                msg = "查询失败，发生异常";
                logger.warn("查询失败，发生异常");
            }
        }
        map = new HashMap<>();

        map.put("code", code);
        map.put("msg", msg);
        map.put("info", blog);

        return map;
    }
}
