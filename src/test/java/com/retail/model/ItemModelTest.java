package com.retail.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ItemModelTest {

    @Test
    public void testThatItemIsCreated() throws Exception {

        Item item = new Item.Builder()
                .id(0)
                .itemName("Books")
                .description("Reading Books")
                .price(new BigDecimal("89.00"))
                .build();

        Assert.assertNotNull(item.getId());
        Assert.assertEquals(item.getDescription(), "Reading Books");
        Assert.assertEquals(item.getItemName(), "Books");
        Assert.assertEquals(item.getPrice(), new BigDecimal("89.00"));
    }
}
