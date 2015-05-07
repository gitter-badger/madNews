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
    private Session session;

    @Override
    public List getUsers() {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User");
        return query.list();
    }

    @Override
    public void create(User entry) {
        session = sessionFactory.getCurrentSession();
        session.save(entry);
    }

    @Override
    public User read(Long id) {
        return (User)session.get(User.class, id);
    }

    @Override
    public void update(User entry) {
        session = sessionFactory.getCurrentSession();
        User user = (User)session.get(User.class, entry.getId());
        user.setFirstname(entry.getFirstname());
        user.setLastname(entry.getLastname());
        user.setEmail(entry.getEmail());
        user.setPassword(entry.getPassword());
        user.setRole(entry.getRole());
        session.save(user);
    }

    @Override
    public void delete(User entry) {
        session = sessionFactory.getCurrentSession();
        session.delete(entry);
    }
}
