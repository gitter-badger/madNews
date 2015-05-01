package org.madnews.dao.impl;

import org.madnews.dao.PostDAO;
import org.madnews.entity.Post;


public class PostDAOImplTest {
    private static PostDAO postDAO = new PostDAOImpl();

    @org.junit.Test
    public void testAddPost() throws Exception {
        Post post = new Post();
        post.setTitle("title 1");
        post.setContent("<html><body>Some Content</body></html>");
        post.setSmallImg("/image/id_small.jpeg");
        post.setBigImg("/image/id_big.jpeg");
        post.setRating(3);
        post.setTimestamp((int) (System.currentTimeMillis() / 1000L));
        postDAO.addPost(post);
    }

    @org.junit.Test
    public void testGetPosts() throws Exception {

    }
}