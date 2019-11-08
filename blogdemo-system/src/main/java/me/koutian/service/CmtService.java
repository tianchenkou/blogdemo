package me.koutian.service;

import me.koutian.bean.Comment;
import me.koutian.bean.Reply;
import me.koutian.mapper.CmtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: KouTian
 * @date: 2019-11-07 10:35
 * @description
 */
@Service
public class CmtService {

    @Autowired
    CmtMapper cmtMapper;

    public Comment getCommentById(int id) {
        return cmtMapper.findCommentById(id);
    }

    public List<Comment> getComments(int aid) {
        return cmtMapper.findAllCommentsByAid(aid);
    }

    public String setCmt(Comment cmt) {
        cmtMapper.insertCmt(cmt);
        return cmt.getId();
    }

    public String setRpy(Reply reply) {
        cmtMapper.insertReply(reply);
        return reply.getId();
    }
}
