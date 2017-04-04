package com.retail.service;

import com.retail.model.Users;
import com.retail.springConfig.ApplicationContextConfig;
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
@ContextConfiguration(classes= {ApplicationContextConfig.class})  // ServletInitializer.class, WebConfig.class
public class UsersServiceTest {

    @Autowired
    private UsersService usersService;
    static int id;

    @Test
    @Transactional
    public void A_testThatUserIsSavedToDatabase() throws Exception {

        Users users = new Users.Builder()
                .username("admin")
                .password("admin")
                .email("admin@admin.com")
                .build();


        Users savedUsers = (Users)  this.usersService.save(users);
        id = users.getId();

        Assert.assertNotNull(id);
        Assert.assertEquals(users.getId(), id);
        Assert.assertEquals(savedUsers.getId(), users.getId());
    }

    @Test
    @Transactional
    public void B_testThatUserIsReadFromDatabase() throws Exception {

        Users user = (Users) this.usersService.findById(id);

        Assert.assertNotNull(user);
        Assert.assertEquals(user.getEmail(), "admin@admin.com");
        Assert.assertEquals(user.getId(), id);
    }

    @Test
    @Transactional
    public void C_testThatThereIsAListOfUsers() throws Exception {
        List<Users> items = this.usersService.findAll();
        Assert.assertTrue(items.size() >= 0);
    }

    @Test
    @Transactional
    public void D_testThatUserIsUpdatedToDatabase() throws Exception {
        Users user = (Users) this.usersService.findById(id);

        Users updateUser= new Users.Builder()
                .updater(user)
                .password("admin_001")
                .build();

        this.usersService.update(updateUser);
        Users newUpdatedUser= (Users) this.usersService.findById(id);

        Assert.assertEquals("admin_001", newUpdatedUser.getPassword());
    }

    @Test
    @Transactional
    public void E_testThatUserIsDeletedFromDatabase() throws Exception {

        Users users = this.usersService.delete(id);
        Users deletedUser= (Users) this.usersService.findById(id);

        Assert.assertNotNull(users);
        Assert.assertNull(deletedUser);

    }
}
