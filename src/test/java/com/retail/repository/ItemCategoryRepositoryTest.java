package com.retail.repository;

import com.retail.springConfig.ApplicationContextConfig;
import com.retail.springConfig.ServletInitializer;
import com.retail.springConfig.SpringWebConfig;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes= {ApplicationContextConfig.class,ServletInitializer.class, SpringWebConfig.class})
public class ItemCategoryRepositoryTest {

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
        ItemCategory itemCategory = new ItemCategory.Builder()
                .categoryName("Books")
                .build();

        ItemCategory savedItemCategory = (ItemCategory)  this.categoryRepository.save(itemCategory);
        id = itemCategory.getId();

        Assert.assertEquals(itemCategory.getId(), id);
        Assert.assertEquals(savedItemCategory.getId(), itemCategory.getId());
        Assert.assertNotNull(id);
    }

    @Test
    @Transactional
    public void B_testThatCategoryIsReadFromDatabase() throws Exception {

        ItemCategory categoryFromDatabase = (ItemCategory) this.categoryRepository.findById(id);

        Assert.assertNotNull(categoryFromDatabase);
        Assert.assertEquals(categoryFromDatabase.getCategoryName(), "Books");
        Assert.assertEquals(categoryFromDatabase.getId(), id);
    }

    @Test
    @Transactional
    public void C_testThatThereIsAListOfCategories() throws Exception {
        List<ItemCategory> categories = this.categoryRepository.findAll();
        Assert.assertTrue(categories.size() >= 0);
    }

    @Test
    @Transactional
    public void D_testThatCategoryIsUpdated() throws Exception {
        ItemCategory category = (ItemCategory) categoryRepository.findById(id);

        ItemCategory updaterCategory = new ItemCategory.Builder()
                .copy(category)
                .categoryName("MEgaBonus")
                .build();

        this.categoryRepository.update(updaterCategory );
        ItemCategory updateCategory = (ItemCategory) categoryRepository.findById(id);
        Assert.assertEquals(updateCategory.getCategoryName(), "MEgaBonus");
    }

    @Test
    @Transactional
    public void E_testThatCategoryIsDeleted() throws Exception {
        ItemCategory category = this.categoryRepository.delete(id);
        ItemCategory deletedCategory = (ItemCategory) categoryRepository.findById(id);

        Assert.assertNotNull(category);
        Assert.assertNull(deletedCategory);
    }
}
