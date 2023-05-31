package raf.webProgramiranje.services;

import raf.webProgramiranje.entities.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);

    boolean deleteCategory(Integer id);

    List<Category> getAllCategories();

    Category changeCategory(Category category);
}
