package com.retail.springConfig;

import com.retail.model.ItemCategory;
import com.retail.repository.CategoryRepository;
import com.retail.repository.Impl.CategoryRepositoryImpl;
import com.retail.repository.Impl.ItemRepositoryImpl;
import com.retail.repository.ItemRepository;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan({
        "com.retail.springConfig", "com.retail.model", "com.retail.repository",
        "com.retail.service", "com.retail.web" })
@EnableTransactionManagement
@EnableWebMvc
public class ApplicationContextConfig {

    @Bean(name = "dataSource")
    public DataSource getDataSource() {

        /*Local MySQL DATA CONNECTION */
        /*BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/what");
        dataSource.setUsername("pelang");
        dataSource.setPassword("password");*/

        /*POSTGRES REMOTE DATA CONNECTION */
        /*BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-107-20-141-145.compute-1.amazonaws.com:5432/d8g2udicueur6h");
        dataSource.setUsername("qnjtnxskfsclqp");
        dataSource.setPassword("50c6e1eb25f54f51d7133bbc57faa66478f21826326a59c8b6cb0c6008d05675");*/

        /*POSTGRES LOCAL DATA CONNECTION */
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/retail");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        return dataSource;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {

        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.addAnnotatedClasses(ItemCategory.class); // my classes
        sessionBuilder.scanPackages("com.retail", "com.retail.springConfig", "com.retail.model", "com.retail.repository", "com.retail.web");
        sessionBuilder.addProperties(getHibernateProperties());

        return sessionBuilder.buildSessionFactory();
    }

    private Properties getHibernateProperties(){

        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
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
}
