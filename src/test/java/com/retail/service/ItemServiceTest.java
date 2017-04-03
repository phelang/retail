package com.retail.service;

import com.retail.springConfig.ApplicationContextConfig;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING) /* Tests Prefixed with A,B,C ...Z */
@ContextConfiguration(classes= {ApplicationContextConfig.class})  // ServletInitializer.class, WebConfig.class
public class ItemServiceTest {

    @Autowired
    ItemService itemService;
    static int id;

    @Test
    @Transactional
    public void A_testThatItemIsSaved() throws Exception {

        Item item = new Item.Builder()
                .itemName("Whisky")
                .item_category_id(3)
                .description("Whisky blended African bride")
                .price(new BigDecimal("78.00"))
                .build();

        Item savedItem = (Item) this.itemService.save(item);
        id = item.getId();

        Assert.assertEquals(item.getId(), id);
        Assert.assertEquals(savedItem.getId(), item.getId());
        Assert.assertNotNull(id);
        Assert.assertEquals(savedItem.getItemCategoryId(), 3);
    }

    @Test
    @Transactional
    public void B_testThatItemIsReadFromDatabase() throws Exception {

        Item item = (Item) this.itemService.findById(id);

        Assert.assertNotNull(item);
        Assert.assertEquals(item.getItemName(), "Whisky");
        Assert.assertEquals(item.getId(), id);
    }
    @Test
    @Transactional
    public void C_testThatThereIsAListOfItems() throws Exception {
        List<Item> items = this.itemService.findAll();
        Assert.assertTrue(items.size() >= 0);
    }

    @Test
    @Transactional
    public void D_testThatItemIsUpdated() throws Exception {
        Item item = (Item) this.itemService.findById(id);

        Item updateItem = new Item.Builder()
                .updater(item)
                .itemName("JackD")
                .build();

        this.itemService.update(updateItem);
        Item newUpdatedItem = (Item) this.itemService.findById(id);

        Assert.assertEquals(newUpdatedItem.getItemName(), "JackD");
    }

    @Test
    @Transactional
    public void E_testThatItemIsDeleted() throws Exception {
        Item item = this.itemService.delete(id);
        Item deletedCategory = (Item) this.itemService.findById(id);

        Assert.assertNotNull(item);
        Assert.assertNull(deletedCategory);
    }
}