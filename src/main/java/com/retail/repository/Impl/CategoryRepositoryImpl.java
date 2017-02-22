package com.retail.repository.Impl;

import com.retail.model.Category;
import com.retail.repository.CategoryRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {
    private static final Logger logger = LoggerFactory.getLogger(CategoryRepositoryImpl.class);

    @Inject
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    //@Override
    public Category save(Category category) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            if(session != null){
                session.close();
            }
        }
         logger.info("Category successfully saved. Category Details : " + category);
        return category;
    }

    //@Override
    public Category findById(Integer id) {
        Session session = null;
        Category category = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            category = (Category) session.load(Category.class,new Integer(id));
            session.getTransaction().commit();

        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            if(session != null){
                session.close();
            }
        }
        //logger.info("Books found.");
        return category;
    }

   // @Override
    @SuppressWarnings("unchecked")
    public List<Category> findAll() {
        Session session = null;
        List<Category> categories = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            categories = session.createQuery("from Category").list();
            session.getTransaction().commit();
            //logger.info("Category successfully saved. Category Details : ");
        }catch (Exception e){
            session.getTransaction().rollback();
            //logger.info("Not List");
        }finally{
            if(session != null){
                session.close();
            }
        }
        return categories;
    }
   // @Override
    public Category update(Category category) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(category);
            session.getTransaction().commit();
            //logger.info("Update Successfull.");
        }catch (Exception e){
            //logger.info("Could not update.");
            session.getTransaction().rollback();
        }finally {
            if(session != null){
                session.close();
            }
        }
        return null;
    }

    /*
    Use findById(Integer id) method and then pass the category object delete(Category category)
     */
   // @Override
    public boolean delete(Category category) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.delete(category);
            session.getTransaction().commit();
            logger.info("Delete successfull");
        }catch (Exception e){
            session.getTransaction().rollback();
            //logger.info("Not Deleted");
        }finally {
            if(session != null){
                session.close();
            }
        }
        return true;
    }
}