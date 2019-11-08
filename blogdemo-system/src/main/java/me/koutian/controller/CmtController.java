package me.koutian.controller;

import me.koutian.annotation.UserLoginToken;
import me.koutian.bean.Comment;
import me.koutian.bean.Reply;
import me.koutian.service.CmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: KouTian
 * @date: 2019-10-31 19:06
 * @description
 */
@RestController
public class CmtController {
    @Autowired
    CmtService cmtService;

    /**
     *          根据评论id获取对应的评论，及其所有子评论
     * @param id
     *          评论id
     * @return
     *          评论对象
     */
    @UserLoginToken
    @GetMapping("/getCmt/{id}")
    public Map<String, Object> getCommentById(@PathVariable("id") int id) {
        Comment comment = null;
        try {
            comment = cmtService.getCommentById(id);
        }catch (Exception e){
            return AppResultBuilder.buildFailedResult("获取评论失败","506");
        }
        if (comment==null){
            return AppResultBuilder.buildFailedResult("该评论为空","404");
        }else{
            HashMap<String, Object> map = new HashMap<>();
            map.put("cmt",comment);
            return AppResultBuilder.buildSuccessResult("获取评论成功","200",map);
        }
    }

    /**
     *          根据文章id获取所有评论
     * @return
     *          评论对象集合
     */
    @UserLoginToken
    @GetMapping("/getAllCmt/{id}")
    public Map<String, Object> getComments(@PathVariable("id") int id) {
        List<Comment> list = null;
        try {
            list = cmtService.getComments(id);
        }catch (Exception e){
            return AppResultBuilder.buildFailedResult("获取评论失败","506");
        }
        if (list==null){
            return AppResultBuilder.buildFailedResult("该文章评论为空","404");
        }else{
            HashMap<String, Object> map = new HashMap<>();
            map.put("cmts",list);
            return AppResultBuilder.buildSuccessResult("获取评论成功","200",map);
        }
    }

    @UserLoginToken
    @PostMapping("/setCmt")
    public Map<String, Object> setComment(@RequestBody Comment cmt){
        String cid = null;
        try{
            cid = cmtService.setCmt(cmt);
        }catch (Exception e){
            return AppResultBuilder.buildFailedResult("插入异常","500");
        }
        if (cid==null){
            return AppResultBuilder.buildFailedResult("插入失败","500");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("cid",cid);
        return AppResultBuilder.buildSuccessResult("插入数据成功","200",map);
    }

    @UserLoginToken
    @PostMapping("/setRpy")
    public Map<String, Object> setComment(@RequestBody Reply reply){
        String rid = null;
        try{
            rid = cmtService.setRpy(reply);
        }catch (Exception e){
//            e.printStackTrace();
            return AppResultBuilder.buildFailedResult("插入异常","500");
        }
        if (rid==null){
            return AppResultBuilder.buildFailedResult("插入失败","500");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("rid",rid);
        return AppResultBuilder.buildSuccessResult("插入数据成功","200",map);
    }

}
