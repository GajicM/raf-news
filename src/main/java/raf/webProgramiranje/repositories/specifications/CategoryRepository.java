package raf.webProgramiranje.repositories.specifications;

import raf.webProgramiranje.entities.Category;

import java.util.List;

public interface CategoryRepository {
    Category addCategory(Category category);

    boolean deleteCategory(Integer category);

    List<Category> getAllCategories();

    Category changeCategory(Category category);
}
