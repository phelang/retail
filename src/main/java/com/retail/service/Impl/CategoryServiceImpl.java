package com.retail.service.Impl;

import com.retail.model.Category;
import com.retail.repository.CategoryRepository;
import com.retail.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    /*
    the exact wording, categoryRepository is wired to a bean id in
    spring configuration xml file, in this project the file name is
    applicationContect.xml
     */
    @Inject
    private CategoryRepository categoryRepository;

    public void setCategoryRepository(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    @Transactional
    public Category findById(Integer id) {
        return this.categoryRepository.findById(id);
    }

    @Transactional
    public List<Category> findAll() {
       return this.categoryRepository.findAll();
    }

    @Transactional
    public Category update(Category category) {
        return this.categoryRepository.update(category);
    }

    @Transactional
    public boolean delete(Category category) {
        return this.categoryRepository.delete(category);
    }
}