package org.madnews.service.impl;

import junit.framework.TestCase;
import org.junit.Test;
import org.madnews.entity.Post;
import org.madnews.service.Service;

public class ServiceImplTest extends TestCase {
    private Service service = new ServiceImpl();

    @Test
    public void testAddPost() throws Exception {
        Post post = new Post();
        post.setTitle("title 1");
        post.setContent("<html><body>Some Content</body></html>");
        post.setSmallImg("/image/id_small.jpeg");
        post.setBigImg("/image/id_big.jpeg");
        post.setRating(3);
        post.setTimestamp((int) (System.currentTimeMillis() / 1000L));
        service.addPost(post);
    }
}