package by.tms.strore.repository;

import by.tms.strore.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsCategoryByName(String name);
    void deleteCategoryByName(String name);
    Category findCategoryByName(String name);
    Category findCategoryById(long id);
    boolean existsCategoryById(long id);
}
