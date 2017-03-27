package com.retail.repository.Impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.retail.model.Item;
import com.retail.repository.ItemRepository;
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
public class ItemRepositoryImpl implements ItemRepository {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepositoryImpl.class);

    @Inject
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    //@Override
    public Item save(Item item) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            if(session != null){
                session.close();
            }
        }
        logger.info("Items saved " + item.toString());
        return item;
    }

    //@Override
    public Item findById(Integer id) {
        Session session = null;
        Item item = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            item = (Item) session.load(Item.class,new Integer(id));
            session.getTransaction().commit();

        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            if(session != null){
                session.close();
            }
        }
        //logger.info("");
        return item;
    }

    //@Override
    @SuppressWarnings("unchecked")
    public List<Item> findAll() {
        Session session = null;
        List<Item> categories = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            categories = session.createQuery("from ITEM").list();
            session.getTransaction().commit();
            //logger.info("Items");
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

    //@Override
    public Item update(Item item) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(item);
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

    //@Override
    public boolean delete(Item item) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.delete(item);
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
