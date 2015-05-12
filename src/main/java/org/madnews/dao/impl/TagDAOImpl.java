package org.madnews.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.madnews.dao.TagDAO;
import org.madnews.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TagDAOImpl implements TagDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void create(Tag entry) {
        getSession().save(entry);
    }

    @Override
    public Tag read(Long id) {
        return (Tag) getSession().get(Tag.class, id);
    }

    @Override
    public void update(Tag entry) {
        Tag tagFromDB = (Tag) getSession().get(Tag.class, entry.getId());
        tagFromDB.setName(entry.getName());
        tagFromDB.setPosts(entry.getPosts());
        getSession().save(tagFromDB);
    }

    @Override
    public void delete(Tag entry) {
        getSession().delete(entry);
    }
}
