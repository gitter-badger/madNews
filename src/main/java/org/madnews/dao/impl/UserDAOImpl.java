package org.madnews.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.madnews.dao.UserDAO;
import org.madnews.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List getUsers() {
        Query query = getSession().createQuery("from User");
        return query.list();
    }

    @Override
    public void create(User entry) {
        getSession().save(entry);
    }

    @Override
    public User read(Long id) {
        return (User)getSession().get(User.class, id);
    }

    @Override
    public void update(User entry) {
        User user = (User)getSession().get(User.class, entry.getId());
        user.setFirstname(entry.getFirstname());
        user.setLastname(entry.getLastname());
        user.setEmail(entry.getEmail());
        user.setPassword(entry.getPassword());
        user.setRole(entry.getRole());
        user.setPosts(entry.getPosts());
        getSession().save(user);
    }

    @Override
    public void delete(User entry) {
        getSession().delete(entry);
    }
}
