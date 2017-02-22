package com.retail.service;
import com.retail.config.ApplicationContextConfig;
import com.retail.config.ServletInitializer;
import com.retail.config.WebConfig;
import com.retail.model.Category;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) /* Tests Prefixed with A,B,C ...Z */
@ContextConfiguration(classes= {ApplicationContextConfig.class})  //,ServletInitializer.class, WebConfig.class
public class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;
    static int id;

    @Test
    @Transactional
    public void A_testThatCategoryIsSaved() throws Exception {
        Category category = new Category.Builder()
                .categoryName("Laptop")
                .build();

        Category savedCategory = (Category) this.categoryService.save(category);
        id = savedCategory.getId();

        Assert.assertNotNull(id);
        Assert.assertEquals(savedCategory.getCategoryName(), "Laptop");
    }

    /*@Test
    @Transactional
    public void B_testThatCategoryIsRead() throws Exception {

        Category category = (Category) this.categoryService.findById(id);

        Assert.assertNotNull(category);
        Assert.assertEquals(category.getCategoryName(), "Laptop");
    }

    @Test
    @Transactional
    public void C_testThatTheresIsCategoryList() throws Exception {
        List<Category> categories = this.categoryService.findAll();

        Assert.assertTrue(categories.size() > 0);
    }

    @Test
    @Transactional
    public void D_testThatCategoryIsUpdated() throws Exception {
        Category category = this.categoryService.findById(id);
        Category updaterCategory = new Category.Builder()
                .copy(category)
                .categoryName("Liquor")
                .build();

        Category updateCategory = (Category) this.categoryService.update(updaterCategory);

        Category newUpdatedCategory = (Category) this.categoryService.findById(id);

        Assert.assertEquals(newUpdatedCategory.getCategoryName(), "Liquor");
    }

    @Test
    @Transactional
    public void E_testThatCategoryIsDeleted() throws Exception {
        Category category = (Category) this.categoryService.findById(id);

        boolean status = this.categoryService.delete(category);
        Category deletedCategory = (Category) this.categoryService.findById(id);

        Assert.assertTrue(status);
        Assert.assertNull(deletedCategory);
    }*/
}
