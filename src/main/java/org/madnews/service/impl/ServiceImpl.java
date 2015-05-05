package org.madnews.service.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.madnews.entity.Post;
import org.madnews.entity.User;
import org.madnews.service.Service;
import org.madnews.utils.HibernateUtil;

import java.util.List;

public class ServiceImpl implements Service {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Session session;


    @Override
    public void addPost(Post post) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(post);
        session.getTransaction().commit();
    }

    @Override
    public List<Post> getPosts() {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User");
        return query.list();
    }

    @Override
    public void addUser(User user) {
        session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public User getUser(Integer id) {
        session = sessionFactory.getCurrentSession();
        User user  = (User) session.get(User.class, id);
        return user;
    }

    @Override
    public List<User> getUsers() {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User");
        return query.list();
    }

    @Override
    public void updateUser(User user) {
        session = sessionFactory.getCurrentSession();
        User userFromDB = (User)session.get(User.class, user.getId());
        userFromDB.setFirstname(user.getFirstname());
        userFromDB.setLastname(user.getLastname());
        userFromDB.setEmail(user.getEmail());
        userFromDB.setPassword(user.getPassword());
        userFromDB.setRole(user.getRole());
        session.save(userFromDB);
    }

    @Override
    public void deleteUser(Integer id) {
        session = sessionFactory.getCurrentSession();
        User user = (User)session.get(User.class, id);
        session.delete(user);
    }
}
