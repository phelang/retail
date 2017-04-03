package com.retail.repository;

import com.retail.springConfig.ApplicationContextConfig;
import com.retail.springConfig.ServletInitializer;
import com.retail.springConfig.SpringWebConfig;
import com.retail.model.Item;
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

import java.math.BigDecimal;
import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes= {ApplicationContextConfig.class,ServletInitializer.class, SpringWebConfig.class})
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;
    static int id;

    @Test
    @Transactional
    /* Rollback and Transactions Management
        @Rollback(true)
        @Transactional
    */
    public void A_testThatItemIsSavedToDatabase()throws Exception
    {
        Item item = new Item.Builder()
                .itemName("Whisky")
                .item_category_id(3)
                .description("Whisky blended African bride")
                .price(new BigDecimal("78.00"))
                .build();

        Item savedItem = (Item)  this.itemRepository.save(item);
        id = item.getId();

        Assert.assertEquals(item.getId(), id);
        Assert.assertEquals(savedItem.getId(), item.getId());
        Assert.assertNotNull(id);
        Assert.assertEquals(savedItem.getItemCategoryId(), 3);
    }

    @Test
    @Transactional
    public void B_testThatItemIsReadFromDatabase() throws Exception {

        Item item = (Item) this.itemRepository.findById(id);

        Assert.assertNotNull(item);
        Assert.assertEquals(item.getItemName(), "Whisky");
        Assert.assertEquals(item.getId(), id);
    }


    @Test
    @Transactional
    public void C_testThatThereIsAListOfItems() throws Exception {
        List<Item> items = this.itemRepository.findAll();
        Assert.assertTrue(items.size() >= 0);
    }

    @Test
    @Transactional
    public void D_testThatItemIsUpdated() throws Exception {
        Item item = (Item) this.itemRepository.findById(id);

        Item updateItem = new Item.Builder()
                .updater(item)
                .itemName("JackD")
                .build();

        this.itemRepository.update(updateItem);
        Item newUpdatedItem = (Item) this.itemRepository.findById(id);

        Assert.assertEquals(newUpdatedItem.getItemName(), "JackD");
    }

    /*@Test
    @Transactional
    @Rollback(true)
    public void E_testThatItemIsDeleted() throws Exception {
        Item item = this.itemRepository.delete(id);
        Item deletedCategory = (Item) this.itemRepository.findById(id);

        Assert.assertNotNull(item);
        Assert.assertNull(deletedCategory);
    }*/
}