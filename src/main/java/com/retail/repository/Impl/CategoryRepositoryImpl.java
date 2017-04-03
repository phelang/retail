package com.retail.repository.Impl;

import com.retail.model.ItemCategory;
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

    public ItemCategory save(ItemCategory itemCategory) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(itemCategory);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            if(session != null){
                session.close();
            }
        }
         logger.info("ItemCategory successfully saved. ItemCategory Details : " + itemCategory);
        return itemCategory;
    }

    public ItemCategory findById(Integer id) {
        Session session = null;
        ItemCategory itemCategory = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            itemCategory = (ItemCategory) session.load(ItemCategory.class,new Integer(id));
            session.getTransaction().commit();

        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            if(session != null){
                session.close();
            }
        }
        //logger.info("Books found.");
        return itemCategory;
    }

    @SuppressWarnings("unchecked")
    public List<ItemCategory> findAll() {
        Session session = null;
        List<ItemCategory> categories = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            categories = session.createQuery("from ITEM_CATEGORY").list();
            session.getTransaction().commit();
            //logger.info("ItemCategory successfully saved. ItemCategory Details : ");
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

    public ItemCategory update(ItemCategory itemCategory) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(itemCategory);
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
    Use findById(Integer id) method and then pass the itemCategory object delete(ItemCategory itemCategory)
     */
    public ItemCategory delete(Integer id) {
        Session session = null;
        ItemCategory toDeleteItemCategory = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            toDeleteItemCategory = this.findById(id);
            session.delete(toDeleteItemCategory);
            session.getTransaction().commit();
            logger.info("");
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.info("");
        }finally {
            if(session != null){
                session.close();
            }
        }
        return toDeleteItemCategory;
    }
}