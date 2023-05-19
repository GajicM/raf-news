package raf.webProgramiranje.repositories.specifications;

import raf.webProgramiranje.entities.Category;

import java.util.List;

public interface CategoryRepository {
    Category addCategory(Category category);

    Category deleteCategory(Category category);

    List<Category> getAllCategories();
}
