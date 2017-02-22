package com.retail;

import com.retail.config.ApplicationContextConfig;
import com.retail.config.ServletInitializer;
import com.retail.config.WebConfig;
import com.retail.model.Category;
import com.retail.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;

/**
 * Created by pelang on 2017/02/22.
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes= {ApplicationContextConfig.class,ServletInitializer.class, WebConfig.class})
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    static int id;

    @Test
    @Transactional
    /* Rollback and Transactions Management
        @Rollback(true)
        @Transactional
     */
    public void A_testThatCategoryIsSavedToDatabase()throws Exception
    {
        Category category = new Category.Builder()
                .categoryName("Books")
                .build();

        Category savedCategory = (Category)  this.categoryRepository.save(category);
        id = category.getId();

        Assert.assertEquals(category.getId(), id);
        Assert.assertEquals(savedCategory.getId(), category.getId());
        Assert.assertNotNull(id);

        System.out.println(savedCategory.getCategoryName());
        System.out.println(savedCategory.getId());
    }


}
