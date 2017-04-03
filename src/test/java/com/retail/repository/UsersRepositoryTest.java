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
    public void testThatUserIsSavedToDatabase() throws Exception {
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
}
