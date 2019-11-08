package me.koutian.bean;

import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;

/**
 * @author: KouTian
 * @date: 2019-10-21 20:24
 * @description
 */
public class Blog {
    private String id;
    private String title;
    private Timestamp date;
    private String author;
    private String url;
    private String content;
    private String catedeId;

    public String getCatedeId() {
        return catedeId;
    }

    public void setCatedeId(String catedeId) {
        this.catedeId = catedeId;
    }

    public Blog() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Blog(String title, Timestamp date, String author, String url, String content) {
        this.title = title;
        this.date = date;
        this.author = author;
        this.url = url;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
