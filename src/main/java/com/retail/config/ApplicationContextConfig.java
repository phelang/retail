package com.retail.config;

import com.retail.model.ItemCategory;
import com.retail.repository.CategoryRepository;
import com.retail.repository.Impl.CategoryRepositoryImpl;
import com.retail.repository.Impl.ItemRepositoryImpl;
import com.retail.repository.ItemRepository;
import com.retail.service.CategoryService;
import com.retail.service.Impl.CategoryServiceImpl;
import com.retail.service.Impl.ItemServiceImpl;
import com.retail.service.ItemService;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan({
        "com.retail.config", "com.retail.model", "com.retail.repository",
        "com.retail.service", "com.retail.web" })
@EnableTransactionManagement
@EnableWebMvc
public class ApplicationContextConfig {

    @Bean(name = "dataSource")
    public DataSource getDataSource() {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/what");
        dataSource.setUsername("pelang");
        dataSource.setPassword("password");

        return dataSource;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {

        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.addAnnotatedClasses(ItemCategory.class); // my classes
        sessionBuilder.scanPackages("com.retail", "com.retail.config", "com.retail.model", "com.retail.repository", "com.retail.web");
        sessionBuilder.addProperties(getHibernateProperties());

        return sessionBuilder.buildSessionFactory();
    }

    private Properties getHibernateProperties(){

        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.c3p0.min_size", "5");
        properties.put("hibernate.c3p0.max_size", "20");
        properties.put("hibernate.c3p0.timeout", "300");
        properties.put("hibernate.c3p0.max_statements", "50");
        properties.put("hibernate.c3p0.idle_test_period", "3000");

        return properties;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(
            SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(
                sessionFactory);

        return transactionManager;
    }

    @Bean(name="categoryRepository")
    public CategoryRepository categoryRepository() {
        return new CategoryRepositoryImpl();
    }

    @Bean(name="itemRepository")
    public ItemRepository itemRepository() {
        return new ItemRepositoryImpl();
    }

    /*@Bean(name="categoryService")
    public CategoryService categoryService() {
        return new CategoryServiceImpl();
    }

    @Bean(name="itemService")
    public ItemService itemService() {
        return new ItemServiceImpl();
    }*/

}
