package me.koutian.mapper;

import me.koutian.bean.Comment;
import me.koutian.bean.Reply;

import java.util.List;

/**
 * @author: KouTian
 * @date: 2019-10-31 19:08
 * @description
 *            对评论和回复进行操作
 */
public interface CmtMapper {
    /**
     *          根据id查找一条评论的所有信息，包括它的所有子评论
     * @param id
     *          一级评论id
     * @return
     *          评论对象
     */
    Comment findCommentById(int id);

    /**
     * 查找所有的评论根据文章id
     * @return
     * 评论对象
     */
    List<Comment>
    findAllCommentsByAid(int aid);

    /**
     * 查找具体的子评论根据id
     * @param id
     *      子评论id
     * @return
     * 回复对象
     */
    Reply findReplyById(int id);


    /**
     *          向数据库插入一条回复(子评论)
     * @param reply
     *          需要插入的回复对象
     */
    void insertReply(Reply reply);

    /**
     *          向数据库插入一条评论
     * @param comment
     *          需要插入的评论对象
     */
    void insertCmt(Comment comment);

    /**
     *          删除评论，根据具体id
     * @param id
     *          评论id
     */
    void deleteCmtById(int id);

    /**
     *          删除回复，根据具体id
     * @param id
     *          评论id
     */
    void deleteRpyById(int id);

    /**
     *             根据用户id查询所有评论
     * @param uid
     *             用户id
     * @return
     *             所有评论集合，没有reply属性
     */
    List<Comment> findAllCmtByUid(int uid);

    /**
     *             根据用户id查询所有回复
     * @param uid
     *             用户id
     * @return
     *             所有回复集合
     */
    List<Reply> findAllRpyByUid(int uid);

    //具体的业务实现





}
