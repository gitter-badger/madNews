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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    public void inserts() {
        List<Role> roles = new ArrayList<>();
        Set<Tag> tags = new HashSet<>();
        Role role1 = new Role(); role1.setName("admin"); roles.add(role1);
        Role role2 = new Role(); role2.setName("writer"); roles.add(role2);
        Role role3 = new Role(); role3.setName("author"); roles.add(role3);
        Role role4 = new Role(); role4.setName("corrector"); roles.add(role4);
        assertNotNull(roleRepository.save(roles));
        Tag tag1 = new Tag(); tag1.setName("sport"); tags.add(tag1);
        Tag tag2 = new Tag(); tag1.setName("music"); tags.add(tag2);
        assertNotNull(tagRepository.save(tags));
        User user = new User();
        user.setFirstname("first");
        user.setLastname("last");
        user.setEmail("test@mail.com");
        user.setPassword("123");
        user.setRole(role1);
        assertNotNull(userRepository.save(user));
        Post post = new Post();
        post.setTitle("title");
        post.setContent("<html><body>Some Content</body></html>");
        post.setSmallImg("/images/small.jpeg");
        post.setBigImg("/images/big.jpeg");
        post.setRating(3);
        post.setIsTopNews(true);
        post.setUser(user);
        post.setTags(tags);
        post.setTimestamp(LocalDateTime.now());
        assertNotNull(postRepository.save(post));
    }
}