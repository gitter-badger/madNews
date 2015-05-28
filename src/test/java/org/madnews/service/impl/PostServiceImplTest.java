package org.madnews.service.impl;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.madnews.config.RootConfig;
import org.madnews.entity.Post;
import org.madnews.repository.PostRepository;
import org.madnews.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@WebAppConfiguration
public class PostServiceImplTest extends TestCase {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertPostTest() {
        Post post = new Post();
        post.setTitle("title ");
        post.setShortText("short text ");
        post.setHtml("<html><body>Some Content</body></html>");
        post.setMainImg("/images/22.jpeg");
        post.setIsTopNews(true);
        post.setIsFeatured(true);
        post.setIsShowOnMain(true);
        post.setUser(userRepository.findOne((long) 1));
        assertNotNull(postRepository.save(post));
        }
    }