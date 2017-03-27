package com.retail.service;
import com.retail.config.ApplicationContextConfig;
import com.retail.model.ItemCategory;
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
public class ItemCategoryServiceTest {

    @Autowired
    CategoryService categoryService;
    static int id;

    @Test
    @Transactional
    public void A_testThatCategoryIsSaved() throws Exception {
        ItemCategory itemCategory = new ItemCategory.Builder()
                .categoryName("Laptop")
                .build();

        ItemCategory savedItemCategory = (ItemCategory) this.categoryService.save(itemCategory);
        id = savedItemCategory.getId();

        Assert.assertNotNull(id);
        Assert.assertEquals(savedItemCategory.getCategoryName(), "Laptop");
    }

    @Test
    @Transactional
    public void B_testThatCategoryIsRead() throws Exception {

        ItemCategory category = (ItemCategory) this.categoryService.findById(id);

        Assert.assertNotNull(category);
        Assert.assertEquals(category.getCategoryName(), "Laptop");
    }

    @Test
    @Transactional
    public void C_testThatTheresIsCategoryList() throws Exception {
        List<ItemCategory> categories = this.categoryService.findAll();
        Assert.assertTrue(categories.size() > 0);
    }

    @Test
    @Transactional
    public void D_testThatCategoryIsUpdated() throws Exception {
        ItemCategory category = this.categoryService.findById(id);
        ItemCategory updaterCategory = new ItemCategory.Builder()
                .copy(category)
                .categoryName("Liquor")
                .build();

        ItemCategory updateCategory = (ItemCategory) this.categoryService.update(updaterCategory);

        ItemCategory newUpdatedCategory = (ItemCategory) this.categoryService.findById(id);

        Assert.assertEquals(newUpdatedCategory.getCategoryName(), "Liquor");
    }

    /*@Test
    @Transactional
    @Rollback
    public void E_testThatCategoryIsDeleted() throws Exception {
        ItemCategory category = (ItemCategory) this.categoryService.findById(id);

        boolean status = this.categoryService.delete(category);
        ItemCategory deletedCategory = (ItemCategory) this.categoryService.findById(id);

        Assert.assertTrue(status);
        Assert.assertNull(deletedCategory);
    }*/
}
