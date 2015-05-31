package org.madnews.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.madnews.entity.Post;
import org.madnews.entity.Tag;
import org.madnews.entity.User;
import org.madnews.service.PostService;
import org.madnews.service.TagService;
import org.madnews.service.UserService;
import org.madnews.utils.ResourceNotFoundException;
import org.madnews.utils.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value="/api/v1/public")
public class PublicNewsController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;

    @RequestMapping(value ="/news/{id}", method = RequestMethod.GET)
    @JsonView(View.FullPost.class)
    public Post getPost(@PathVariable Long id){
        Post post = postService.readPost(id);
        if (post==null){
            throw new ResourceNotFoundException();
        }
        return post;
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    @JsonView(View.ShortPost.class)
    public Iterable<?> getNews(){
        List posts = (List) postService.getPosts();
        if (posts.size()==0){
            throw new ResourceNotFoundException();
        }
        return posts;
    }

    @RequestMapping(value = "/news/tag/{id}", method = RequestMethod.GET)
    @JsonView(View.ShortPost.class)
    public Set<?> getPostsByTagId(@PathVariable Long id){
        Tag tag = tagService.readTag(id);
        if (tag == null){
            throw new ResourceNotFoundException();
        }
        Set posts = tag.getPosts();
        if (posts.size()==0){
            throw new ResourceNotFoundException();
        }
        return posts;
    }

    @RequestMapping(value = "/news/top", method = RequestMethod.GET)
    public Post getTodayTopNews(){
        Post post = postService.getTodayTopNews();
        if (post==null){
            throw new ResourceNotFoundException();
        }
        return post;
    }

    @RequestMapping(value ="/users/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id){
        User user = userService.getUser(id);
        if (user==null){
            throw new ResourceNotFoundException();
        }
        return user;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Iterable<User> getUsers(){
        List users = (List) userService.getUsers();
        if (users.size()==0){
            throw new ResourceNotFoundException();
        }
        return users;
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public Iterable<Tag> getTags() {
        List tags = (List) tagService.getTags();
        if (tags.size()==0){
            throw new ResourceNotFoundException();
        }
        return tags;
    }
}
