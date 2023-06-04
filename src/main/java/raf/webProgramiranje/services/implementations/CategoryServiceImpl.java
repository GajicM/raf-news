package raf.webProgramiranje.services.implementations;

import raf.webProgramiranje.entities.Category;
import raf.webProgramiranje.repositories.specifications.CategoryRepository;
import raf.webProgramiranje.services.CategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Inject
    CategoryRepository categoryRepository;
    @Override
    public Category addCategory(Category category) {
        return categoryRepository.addCategory(category);
    }

    @Override
    public boolean deleteCategory(Integer category) {
      return   categoryRepository.deleteCategory(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }
@Override
    public Category changeCategory(Category category) {
        return categoryRepository.changeCategory(category);
    }
    @Override
    public List<Category> getAllCategories(int offset, int limit){
        return categoryRepository.getAllCategories(offset,limit);
    }
}
