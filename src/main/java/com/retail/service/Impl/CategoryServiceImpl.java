package com.retail.service.Impl;

import com.retail.model.ItemCategory;
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
    public ItemCategory save(ItemCategory itemCategory) {
        return this.categoryRepository.save(itemCategory);
    }

    @Transactional
    public ItemCategory findById(Integer id) {
        return this.categoryRepository.findById(id);
    }

    @Transactional
    public List<ItemCategory> findAll() {
       return this.categoryRepository.findAll();
    }

    @Transactional
    public ItemCategory update(ItemCategory itemCategory) {
        return this.categoryRepository.update(itemCategory);
    }

    @Transactional
    public boolean delete(ItemCategory itemCategory) {
        return this.categoryRepository.delete(itemCategory);
    }
}