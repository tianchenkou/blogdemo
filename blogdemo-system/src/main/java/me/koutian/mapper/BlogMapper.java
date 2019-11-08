package me.koutian.mapper;


import me.koutian.bean.Blog;

import java.util.List;

public interface BlogMapper {

    /**
     * 根据二级目录的信息来返回相对应的具体blog信息
     *
     * @param i 二级目录的id
     * @return 对应的blog集合
     */
    List<Blog> getBlogsByCateDeId(int i);

    void saveBlog(Blog blog);

    Blog getBlogById(Integer id);
}
