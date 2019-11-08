package me.koutian.bean;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author: KouTian
 * @date: 2019-11-06 16:46
 * @description
 *          评论实体类
 */
public class Comment{
    private String id;
    private String articleId;       //所回复文章的id
    private String fromId;          //评论者id
    private String fromName;        //评论者昵称
    private String fromAvatar;      //评论者头像
    private String likeNum;         //点赞数
    private String content;         //评论内容
    private Timestamp createdTime;  //创建时间

    private List<Reply> replys;     //属于本评论的所有回复

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", articleId='" + articleId + '\'' +
                ", fromId='" + fromId + '\'' +
                ", fromName='" + fromName + '\'' +
                ", fromAvatar='" + fromAvatar + '\'' +
                ", likeNum='" + likeNum + '\'' +
                ", content='" + content + '\'' +
                ", createdTime=" + createdTime +
                ", replys=" + replys +
                '}';
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromAvatar() {
        return fromAvatar;
    }

    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public List<Reply> getReplys() {
        return replys;
    }

    public void setReplys(List<Reply> replys) {
        this.replys = replys;
    }
}
