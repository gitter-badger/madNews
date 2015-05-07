package org.madnews.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.madnews.dao.PostDAO;
import org.madnews.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDAOImpl implements PostDAO {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    @Override
    public List readPosts() {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Post");
        return query.list();
    }

    @Override
    public void create(Post entry) {
        session = sessionFactory.getCurrentSession();
        session.save(entry);
    }

    @Override
    public Post read(Long id) {
        return (Post)session.get(Post.class, id);
    }

    @Override
    public void update(Post entry) {
        session = sessionFactory.getCurrentSession();
        Post postFromDb = (Post)session.get(Post.class, entry.getId());
        postFromDb.setContent(entry.getContent());
        postFromDb.setTitle(entry.getTitle());
        postFromDb.setSmallImg(entry.getSmallImg());
        postFromDb.setBigImg(entry.getBigImg());
        postFromDb.setRating(entry.getRating());
        postFromDb.setTimestamp(entry.getTimestamp());
        session.save(postFromDb);
    }

    @Override
    public void delete(Post entry) {
        session = sessionFactory.getCurrentSession();
        session.delete(entry);
    }
}
