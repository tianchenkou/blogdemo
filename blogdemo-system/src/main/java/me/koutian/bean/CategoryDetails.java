package me.koutian.bean;

/**
 * @author: KouTian
 * @date: 2019-10-21 20:22
 * @description
 */
public class CategoryDetails {
    private String id;
    private String name;
    private Category parent;
    private String parentId;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public CategoryDetails(String name) {
        this.name = name;
    }

    public CategoryDetails() {
    }

    @Override
    public String toString() {
        return "CategoryDetails{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
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
}
