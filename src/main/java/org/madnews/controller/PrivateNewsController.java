package org.madnews.controller;

import org.madnews.entity.Post;
import org.madnews.entity.User;
import org.madnews.service.PostService;
import org.madnews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/v1/private")
public class PrivateNewsController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/news", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void postNews(@RequestBody Post post){
        postService.createPost(post);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void postUser(@RequestBody User user){
        userService.addUser(user);
    }
}
