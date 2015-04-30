package org.madnews.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.madnews.dao.PostDAO;
import org.madnews.entity.Post;

import javax.annotation.Resource;
import java.util.List;

public class PostDAOImpl implements PostDAO{

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
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
