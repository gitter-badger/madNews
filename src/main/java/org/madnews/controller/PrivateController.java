package org.madnews.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.madnews.entity.Post;
import org.madnews.entity.User;
import org.madnews.service.PostService;
import org.madnews.service.UserService;
import org.madnews.utils.EmailResponseWrapper;
import org.madnews.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value="/api/v1/private")
public class PrivateController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/news", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Post postNews(@RequestBody Post post){
        return postService.createPost(post);
    }
    
    @RequestMapping(value ="/users/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id){
        User user = userService.readUser(id);
        if (user==null){
            throw new ResourceNotFoundException();
        }
        return user;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List getUsers(){
        List users = (List) userService.readUsers();
        if (users.size()==0){
            throw new ResourceNotFoundException();
        }
        return users;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User postUser(@RequestBody User user){
        return userService.createUser(user);
    }
    
    @RequestMapping(value = "/helpers/email-not-in-db/{email:^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$}", method = RequestMethod.GET)
    public ResponseEntity<EmailResponseWrapper> isUserByEmail(@PathVariable String email){
    	boolean result = userService.hasUserByEmail(email);
    	EmailResponseWrapper requestWrapper = new EmailResponseWrapper(email, result);
    	return new ResponseEntity<>(requestWrapper, HttpStatus.OK);
    }

    @RequestMapping(value="/image", method=RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("src/main/webapp/images/"+file.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
                return "{\"link\":\"//images//"+file.getOriginalFilename()+"\"}";
            } catch (Exception e) {
                return "You failed to upload => " + e.getMessage();
            }
        } else {
            return "You failed to upload because the file was empty.";
        }
    }
}
