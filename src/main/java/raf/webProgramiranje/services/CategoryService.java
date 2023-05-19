package raf.webProgramiranje.services;

import raf.webProgramiranje.entities.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);

    Category deleteCategory(Category category);

    List<Category> getAllCategories();
}
