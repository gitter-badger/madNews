package org.madnews.service.impl;

import junit.framework.TestCase;
import org.junit.Test;
import org.madnews.entity.Post;
import org.madnews.service.PostService;

public class PostServiceImplTest extends TestCase {
    private PostService postService = new PostServiceImpl();

    @Test
    public void testAddAndGetPost() throws Exception {
        Post post = new Post();
        post.setTitle("title 2");
        post.setContent("<html><body>Some Content</body></html>");
        post.setSmallImg("/image/id_small.jpeg");
        post.setBigImg("/image/id_big.jpeg");
        post.setRating(3);
        post.setTimestamp((int) (System.currentTimeMillis() / 1000L));
        postService.createPost(post);
        Post postFromDB = postService.readPost(post.getId());
        assertEquals(postFromDB.getTitle().trim(), post.getTitle());
    }

    @Test
    public void testGetAndUpdatePost() throws Exception {
        Long id = new Long(1);
        String newTitle = "new title";
        Post post = postService.readPost(id); //retrieve post by id from DB
        assertNotNull(post);
        post.setTitle(newTitle);
        postService.updatePost(post);
        post = postService.readPost(id);
        assertEquals(newTitle, post.getTitle());
    }
}