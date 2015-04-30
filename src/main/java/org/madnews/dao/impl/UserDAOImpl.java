package org.madnews.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.madnews.dao.UserDAO;
import org.madnews.entity.User;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by andrey on 4/26/2015.
 */
public class UserDAOImpl implements UserDAO {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    private Session session;

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
