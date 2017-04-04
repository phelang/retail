package com.retail.repository.Impl;

import com.retail.model.UserRole;
import com.retail.repository.UserRoleRepository;
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
public class UserRoleRepositoryImpl implements UserRoleRepository {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepositoryImpl.class);

    @Inject
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public UserRole save(UserRole userRole) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(userRole);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        logger.info("");
        return userRole;
    }

    public UserRole findById(Integer id) {
        Session session = null;
        UserRole user = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            user = (UserRole) session.load(UserRole.class,new Integer(id));
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
    public List<UserRole> findAll() {
        Session session = null;
        List<UserRole> users = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            users = session.createQuery("from USER_ROLE").list();
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

    public UserRole update(UserRole userRole) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(userRole);
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

    public UserRole delete(Integer id) {
        Session session = null;
        UserRole toDeleteUserRole = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            toDeleteUserRole = this.findById(id);
            session.delete(toDeleteUserRole);
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
        return toDeleteUserRole;
    }
}
