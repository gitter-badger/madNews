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

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List readPosts() {
        Query query = getSession().createQuery("from Post");
        return query.list();
    }

    @Override
    public Post getTodayTopNews() {
        return (Post) getSession().createQuery("from Post where isTopNews=true and date=current_date").uniqueResult();
    }

    @Override
    public void create(Post entry) {
        getSession().save(entry);
    }

    @Override
    public Post read(Long id) {
        return (Post)getSession().get(Post.class, id);
    }

    @Override
    public void update(Post entry) {
        Post postFromDb = (Post)getSession().get(Post.class, entry.getId());
        postFromDb.setContent(entry.getContent());
        postFromDb.setTitle(entry.getTitle());
        postFromDb.setSmallImg(entry.getSmallImg());
        postFromDb.setBigImg(entry.getBigImg());
        postFromDb.setRating(entry.getRating());
        postFromDb.setTags(entry.getTags());
        postFromDb.setIsTopNews(entry.isTopNews());
        postFromDb.setUser(entry.getUser());
        postFromDb.setDate(entry.getDate());
        postFromDb.setTime(entry.getTime());
        getSession().save(postFromDb);
    }

    @Override
    public void delete(Post entry) {
        getSession().delete(entry);
    }
}
