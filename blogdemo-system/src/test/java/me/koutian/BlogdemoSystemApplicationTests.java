package me.koutian;

import me.koutian.bean.Comment;
import me.koutian.mapper.CmtMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogdemoSystemApplicationTests {
    @Autowired
    private CmtMapper cmtMapper;
    @Test
    public void contextLoads() {
//        Comment comment = cmtMapper.findCommentById(1);
//        System.out.println(comment);
        List<Comment> list = cmtMapper.findAllCommentsByAid(1);
        System.out.println(list);
//        Reply reply = new Reply();
//        reply.setCommentId("1");
//        reply.setContent("这是一条测试文本");
//        reply.setFromId("0");
//        cmtMapper.insertReply(reply);
//        System.out.println(reply.getId());
    }

}
