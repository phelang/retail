package com.retail.repository;

import com.retail.model.Users;
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
public class UsersRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    static int id;

    @Test
    @Transactional
    public void A_testThatUserIsSavedToDatabase() throws Exception {
        Users users = new Users.Builder()
                .username("users")
                .password("uyy1")
                .email("users@users.com")
                .build();


        Users savedUsers = (Users)  this.userRepository.save(users);
        id = users.getId();

        Assert.assertNotNull(id);
        Assert.assertEquals(users.getId(), id);
        Assert.assertEquals(savedUsers.getId(), users.getId());
    }

    @Test
    @Transactional
    public void B_testThatUserIsReadFromDatabase() throws Exception {

        Users user = (Users) this.userRepository.findById(id);

        Assert.assertNotNull(user);
        Assert.assertEquals(user.getEmail(), "users@users.com");
        Assert.assertEquals(user.getId(), id);
    }

    @Test
    @Transactional
    public void C_testThatThereIsAListOfUsers() throws Exception {
        List<Users> items = this.userRepository.findAll();
        Assert.assertTrue(items.size() >= 0);
    }

    @Test
    @Transactional
    public void D_testThatUserIsUpdatedToDatabase() throws Exception {
        Users user = (Users) this.userRepository.findById(id);

        Users updateUser= new Users.Builder()
                .updater(user)
                .password("new_password")
                .build();

        this.userRepository.update(updateUser);
        Users newUpdatedUser= (Users) this.userRepository.findById(id);

        Assert.assertEquals("new_password", newUpdatedUser.getPassword());
    }

    @Test
    @Transactional
    public void E_testThatUserIsDeletedFromDatabase() throws Exception {

        Users users = this.userRepository.delete(id);
        Users deletedUser= (Users) this.userRepository.findById(id);

        Assert.assertNotNull(users);
        Assert.assertNull(deletedUser);

    }
}
