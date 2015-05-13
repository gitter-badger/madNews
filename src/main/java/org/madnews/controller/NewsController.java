package org.madnews.controller;

import org.madnews.entity.Post;
import org.madnews.entity.User;
import org.madnews.service.PostService;
import org.madnews.service.TagService;
import org.madnews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value="/api/v1/public")
public class NewsController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;

    @RequestMapping(value ="/news/{id}")
    public Post getPost(@PathVariable Long id){
        return postService.readPost(id);
    }

    @RequestMapping(value = "/news")
    public List getNews(){
        return postService.getPosts();
    }

    @RequestMapping(value = "/news/?tag={id}")
    public Set getPostsByTagId(@PathVariable Long id){
        return tagService.getPostsByTagId(id);
    }

    @RequestMapping(value = "/news/top")
    public Post getTodayTopNews(){
        return postService.getTodayTopNews();
    }

    @RequestMapping(value ="/users/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @RequestMapping(value = "/users")
    public List getUsers(){
        return userService.getUsers();
    }

    @RequestMapping(value = "/tags")
    public List getTags() {
        return tagService.getTags();
    }
}
