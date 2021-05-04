package by.tms.strore.service;

import by.tms.strore.entity.Category;
import by.tms.strore.exception.CategoryAlreadyExistsException;
import by.tms.strore.exception.CategoryNotFoundException;
import by.tms.strore.repository.CategoryRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void createCategory(Category category) {
        if (!categoryRepository.existsCategoryByName(category.getName())) {
            categoryRepository.save(category);
        } else {
            throw new CategoryAlreadyExistsException("Category is exists");
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories != null) {
            return categories;
        } else {
            throw new CategoryNotFoundException("Categories not found");
        }
    }

    public Category findCategoryByName(String name) {
        if (categoryRepository.existsCategoryByName(name)) {
            return categoryRepository.findCategoryByName(name);
        } else {
            throw new CategoryNotFoundException("Category not found");
        }
    }
}
