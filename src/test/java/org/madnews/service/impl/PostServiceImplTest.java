package org.madnews.service.impl;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.madnews.config.RootConfig;
import org.madnews.repository.PermissionRepository;
import org.madnews.repository.PostRepository;
import org.madnews.repository.TagRepository;
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
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    public void inserts() {
/*
        Set<Permission> permissions = new HashSet<>();
        Set<Tag> tags = new HashSet<>();
        Permission role1 = new Permission(); role1.setName("user-management"); permissions.add(role1);
        Permission role2 = new Permission(); role2.setName("news-add-edit-delete-own"); permissions.add(role2);
        Permission role3 = new Permission(); role3.setName("news-edit-any"); permissions.add(role3);
        Permission role4 = new Permission(); role4.setName("news-edit-delete-any "); permissions.add(role4);
        assertNotNull(permissionRepository.save(permissions));
        Tag tag1 = new Tag(); tag1.setName("sport"); tags.add(tag1);
        Tag tag2 = new Tag(); tag2.setName("music"); tags.add(tag2);
        assertNotNull(tagRepository.save(tags));
        User user = new User();
        user.setFirstname("first");
        user.setLastname("last");
        user.setEmail("test@mail.com");
        user.setPassword("123");
        user.setPermissions(permissions);
        assertNotNull(userRepository.save(user));
        for (int i=0; i<51; i++) {
            Post post = new Post();
            post.setTitle("title " + i);
            post.setShortText("short text " + i);
            post.setHtml("<html><body>Some Content " + i + "</body></html>");
            post.setMainImg("/images/" + i + ".jpeg");
            if (i % 2 == 0) {
                post.setIsTopNews(true);
            }
            if (i % 2 != 0) {
                post.setIsFeatured(true);
            }
            post.setIsShowOnMain(true);
            post.setUser(user);
            post.setTags(tags);
            post.setTimestamp(new Timestamp(System.currentTimeMillis()));
            assertNotNull(postRepository.save(post));
        }
        */
    }

}