package me.koutian.bean;

import java.util.List;

/**
 * @author: KouTian
 * @date: 2019-10-21 20:21
 * @description 一级目录
 */
public class Category {
    private String id;
    private String name;
    private List<CategoryDetails> sonCate;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryDetails> getSonCate() {
        return sonCate;
    }

    public void setSonCate(List<CategoryDetails> sonCate) {
        this.sonCate = sonCate;
    }
}
