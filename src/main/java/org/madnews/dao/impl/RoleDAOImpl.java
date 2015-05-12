package org.madnews.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.madnews.dao.RoleDAO;
import org.madnews.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void create(Role entry) {
        getSession().save(entry);
    }

    @Override
    public Role read(Long id) {
        return (Role) getSession().get(Role.class, id);
    }

    @Override
    public void update(Role entry) {
        Role roleFromDB = (Role) getSession().get(Role.class, entry.getId());
        roleFromDB.setName(entry.getName());
        roleFromDB.setUser(entry.getUser());
    }

    @Override
    public void delete(Role entry) {
        getSession().delete(entry);
    }
}
