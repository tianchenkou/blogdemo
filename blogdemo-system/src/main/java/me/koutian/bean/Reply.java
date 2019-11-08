package me.koutian.bean;

import java.sql.Timestamp;

/**
 * @author: KouTian
 * @date: 2019-11-06 16:46
 * @description
 *          回复实体类
 */
public class Reply {
    private String id;
    private String commentId;       //所回复评论的id(一级评论)
    private String fromId;          //评论者id
    private String fromName;        //评论者昵称
    private String fromAvatar;      //评论者头像
    private String toId;            //使用'@'时，@某人的id
    private String toName;          //使用'@'时，@某人的昵称
    private String toAvatar;        //使用'@'时，@某人的头像
    private String content;         //评论内容
    private Timestamp createdTime;  //创建时间

    @Override
    public String toString() {
        return "Reply{" +
                "id='" + id + '\'' +
                ", commentId='" + commentId + '\'' +
                ", fromId='" + fromId + '\'' +
                ", fromName='" + fromName + '\'' +
                ", fromAvatar='" + fromAvatar + '\'' +
                ", toId='" + toId + '\'' +
                ", toName='" + toName + '\'' +
                ", toAvatar='" + toAvatar + '\'' +
                ", content='" + content + '\'' +
                ", createdTime=" + createdTime +
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

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToAvatar() {
        return toAvatar;
    }

    public void setToAvatar(String toAvatar) {
        this.toAvatar = toAvatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
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
}
