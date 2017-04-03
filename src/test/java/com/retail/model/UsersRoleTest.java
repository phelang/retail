package com.retail.model;

import org.junit.Assert;
import org.junit.Test;

public class UsersRoleTest {

    @Test
    public void testThatUserRoleIsCreated() throws Exception {

        UserRole userRole = new UserRole.Builder()
                .id(1)
                .userId(1)
                .role("user")
                .build();

        Assert.assertEquals(1, userRole.getId());
        Assert.assertEquals(1, userRole.getUserId());
        Assert.assertEquals("user", userRole.getRole());
    }
}
