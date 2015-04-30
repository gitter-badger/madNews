package org.madnews.dao.impl;

import org.madnews.dao.PostDAO;
import org.madnews.entity.Post;

import static org.junit.Assert.*;

/**
 * Created by andrey.bereza on 30.04.2015.
 */
public class PostDAOImplTest {
    private static PostDAO postDAO = new PostDAOImpl();

    @org.junit.Test
    public void testAddPost() throws Exception {
        Post post = new Post();
        post.setTitle("title 1");
        post.setContent("<html><body>Some Content</body></html>");
        post.setSmall_img("/image/id_small.jpeg");
        post.setBig_img("/image/id_big.jpeg");
        post.setRating(3);
        post.setTimestamp((int) (System.currentTimeMillis() / 1000L));

        postDAO.addPost(post);
    }

    @org.junit.Test
    public void testGetPosts() throws Exception {

    }
}