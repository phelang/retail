package com.retail.repository;

import com.retail.model.UserRole;
import com.retail.springConfig.ApplicationContextConfig;
import com.retail.springConfig.ServletInitializer;
import com.retail.springConfig.SpringWebConfig;
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
public class UserRoleRepositoryTest {

    @Autowired
    private UserRoleRepository userRoleRepository;
    static int id;

    @Test
    @Transactional
    public void A_testThatUserRolseIsSavedToDatabase() throws Exception {

        UserRole userRole = new UserRole.Builder()
                .id(1)
                .userId(1)
                .role("user")
                .build();

        UserRole savedUserRole = (UserRole)  this.userRoleRepository.save(userRole);
        id = userRole.getId();

        Assert.assertNotNull(id);
        Assert.assertEquals(userRole.getId(), id);
        Assert.assertEquals(savedUserRole.getId(), userRole.getId());
    }

    @Test
    @Transactional
    public void B_testThatUserRoleIsReadFromDatabase() throws Exception {


        UserRole userRole = (UserRole) this.userRoleRepository.findById(id);

        Assert.assertNotNull(userRole);
        Assert.assertEquals(userRole.getRole(), "user");
        Assert.assertEquals(userRole.getId(), id);
    }

    @Test
    @Transactional
    public void C_testThatThereIsAListOfUserRoles() throws Exception {

        List<UserRole> items = this.userRoleRepository.findAll();
        Assert.assertTrue(items.size() >= 0);

    }


    @Test
    @Transactional
    public void D_testThatUserRoleIsUpdatedInDatabase() throws Exception {
        UserRole userRole = (UserRole) this.userRoleRepository.findById(id);

        UserRole updateUserRole = new UserRole.Builder()
                .updater(userRole)
                .role("administrator")
                .build();

        this.userRoleRepository.update(updateUserRole);
        UserRole newUpdatedUserRole = (UserRole) this.userRoleRepository.findById(id);

        Assert.assertEquals("administrator", newUpdatedUserRole.getRole());

    }

    @Test
    @Transactional
    public void E_testThatUserRoleIsDeletedFromDatabase() throws Exception {

        UserRole userRole = this.userRoleRepository.delete(id);
        UserRole deletedUserRole = (UserRole) this.userRoleRepository.findById(id);

        Assert.assertNotNull(userRole);
        Assert.assertNull(deletedUserRole);

    }
}
