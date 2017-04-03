package com.retail.model;

import org.junit.Assert;
import org.junit.Test;

public class UsersTest {

    @Test
    public void tesThatUserIsCreated() throws Exception {
        Users users = new Users.Builder()
                .id(1)
                .username("users")
                .password("uyy1")
                .email("users@users.com")
                .build();

        Assert.assertEquals(1, users.getId());
        Assert.assertEquals("users", users.getUsername());
        Assert.assertEquals("uyy1", users.getPassword());
        Assert.assertEquals("users@users.com", users.getEmail());
    }
}
