package com.retail.model;

import org.junit.Assert;
import org.junit.Test;

public class ItemCategoryTest {

    @Test
    public void testThatItemCategoryIsCreated() throws Exception {

        ItemCategory itemCategory = new ItemCategory.Builder()
                .id(1)
                .categoryName("Books")
                .build();

        Assert.assertEquals(1, itemCategory.getId());
        Assert.assertEquals("Books", itemCategory.getCategoryName());
    }
}
