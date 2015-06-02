package org.madnews.controller;

import org.madnews.entity.Post;
import org.madnews.entity.User;
import org.madnews.service.PostService;
import org.madnews.service.UserService;
import org.madnews.utils.EmailResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    public Post postNews(@RequestBody Post post){
        return postService.createPost(post);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User postUser(@RequestBody User user){
        return userService.addUser(user);
    }
    
    @RequestMapping(value = "/helpers/email-not-in-db/{email:^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$}", method = RequestMethod.GET)
    public ResponseEntity<EmailResponseWrapper> isUserByEmail(@PathVariable String email){
    	boolean result = userService.hasUserByEmail(email);
    	EmailResponseWrapper requestWrapper = new EmailResponseWrapper(email, result);
    	return new ResponseEntity<EmailResponseWrapper>(requestWrapper, HttpStatus.OK);
    }
}
