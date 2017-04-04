package com.retail.service;

import com.retail.model.UserRole;
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
public class UserRoleServiceTest {

    @Autowired
    private UserRoleService userRoleService;
    static int id;

    @Test
    @Transactional
    public void A_testThatUserRolseIsSavedToDatabase() throws Exception {

        UserRole userRole = new UserRole.Builder()
                .id(1)
                .userId(1)
                .role("administrator")
                .build();

        UserRole savedUserRole = (UserRole)  this.userRoleService.save(userRole);
        id = userRole.getId();

        Assert.assertNotNull(id);
        Assert.assertEquals(userRole.getId(), id);
        Assert.assertEquals(savedUserRole.getId(), userRole.getId());
    }

    @Test
    @Transactional
    public void B_testThatUserRoleIsReadFromDatabase() throws Exception {


        UserRole userRole = (UserRole) this.userRoleService.findById(id);

        Assert.assertNotNull(userRole);
        Assert.assertEquals(userRole.getRole(), "administrator");
        Assert.assertEquals(userRole.getId(), id);
    }

    @Test
    @Transactional
    public void C_testThatThereIsAListOfUserRoles() throws Exception {

        List<UserRole> items = this.userRoleService.findAll();
        Assert.assertTrue(items.size() >= 0);

    }


    @Test
    @Transactional
    public void D_testThatUserRoleIsUpdatedInDatabase() throws Exception {
        UserRole userRole = (UserRole) this.userRoleService.findById(id);

        UserRole updateUserRole = new UserRole.Builder()
                .updater(userRole)
                .role("manager")
                .build();

        this.userRoleService.update(updateUserRole);
        UserRole newUpdatedUserRole = (UserRole) this.userRoleService.findById(id);

        Assert.assertEquals("manager", newUpdatedUserRole.getRole());

    }

    @Test
    @Transactional
    public void E_testThatUserRoleIsDeletedFromDatabase() throws Exception {

        UserRole userRole = this.userRoleService.delete(id);
        UserRole deletedUserRole = (UserRole) this.userRoleService.findById(id);

        Assert.assertNotNull(userRole);
        Assert.assertNull(deletedUserRole);
    }
}
