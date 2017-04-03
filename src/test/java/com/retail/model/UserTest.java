package com.retail.model;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void tesThatUserIsCreated() throws Exception {
        User user = new User.Builder()
                .id(1)
                .username("user")
                .password("uyy1")
                .email("user@user.com")
                .build();

        Assert.assertEquals(1, user.getId());
        Assert.assertEquals("user", user.getUsername());
        Assert.assertEquals("uyy1", user.getPassword());
        Assert.assertEquals("user@user.com", user.getEmail());
    }
}
