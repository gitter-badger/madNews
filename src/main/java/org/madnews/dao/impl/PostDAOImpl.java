package org.madnews.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.madnews.dao.PostDAO;
import org.madnews.entity.Post;
import org.madnews.utils.HibernateUtils;

import java.util.List;

public class PostDAOImpl implements PostDAO{
    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private Session session;

    @Override
    public void addPost(Post post) {
        session = sessionFactory.getCurrentSession();
        session.save(post);
    }

    public List<Post> getPosts() {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User");
        return query.list();
    }
}
