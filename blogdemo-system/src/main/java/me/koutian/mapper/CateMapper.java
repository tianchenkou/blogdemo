package me.koutian.mapper;

import me.koutian.bean.Category;
import me.koutian.bean.CategoryDetails;

import java.util.List;

public interface CateMapper {
    Category findCateById(int i);

    List<Category> findAllCate();

    void insertCategoryDetail(CategoryDetails categoryDetails);

    void insertCategoryDetails(CategoryDetails categoryDetails);

    void insertCate(Category category);
}
