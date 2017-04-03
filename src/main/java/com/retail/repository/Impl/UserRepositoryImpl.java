package com.retail.repository.Impl;

import com.retail.model.Users;
import com.retail.repository.UserRepository;
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
public class UserRepositoryImpl implements UserRepository{

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepositoryImpl.class);

    @Inject
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public Users save(Users users) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(users);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        logger.info("User saved " + users.toString());
        return users;
    }

    public Users findById(Integer id) {
        Session session = null;
        Users user = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            user = (Users) session.load(Users.class,new Integer(id));
            session.getTransaction().commit();

        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            if(session != null){
                session.close();
            }
        }
        //logger.info("");
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<Users> findAll() {
        Session session = null;
        List<Users> users = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            users = session.createQuery("from USERS").list();
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
        return users;
    }

    public Users update(Users user) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            logger.info("");
        } catch (Exception e) {
            //logger.info("Could not update.");
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public Users delete(Integer id) {
        Session session = null;
        Users toDeleteItem = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            toDeleteItem = this.findById(id);
            session.delete(toDeleteItem);
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
        return toDeleteItem;
    }
}
