package org.madnews.service.impl;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.madnews.config.RootConfig;
import org.madnews.entity.Post;
import org.madnews.entity.Role;
import org.madnews.entity.Tag;
import org.madnews.entity.User;
import org.madnews.repository.PostRepository;
import org.madnews.repository.RoleRepository;
import org.madnews.repository.TagRepository;
import org.madnews.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@WebAppConfiguration
public class PostServiceImplTest extends TestCase {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testAddPost() throws Exception{
        User user = userRepository.findOne((long) 0);
        Set<Tag> tags = new HashSet<>();
        tags.add(tagRepository.findOne((long) 0));
        tags.add(tagRepository.findOne((long) 1));
        //////////////////////////////////////
        Post post = new Post();
        post.setTitle("title");
        post.setContent("<html><body>Some Content</body></html>");
        post.setSmallImg("/images/small.jpeg");
        post.setBigImg("/images/big.jpeg");
        post.setRating(3);
        post.setIsTopNews(true);
        post.setUser(user);
        post.setTags(tags);
        postRepository.save(post);
    }

    @Test
    public void testCreateAndReadUser() throws Exception{
      /*  Role role = new Role();
        role.setName("admin");
        assertNotNull(roleRepository.save(role));*/
        User user = new User();
        user.setFirstname("first");
        user.setLastname("last");
        user.setEmail("test@mail.com");
        user.setPassword("123");
        user.setRole(roleRepository.findOne((long)0));
        assertNotNull(userRepository.save(user));
    }

}