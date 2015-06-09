package org.madnews.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.madnews.entity.Post;
import org.madnews.entity.Tag;
import org.madnews.entity.User;
import org.madnews.service.PostService;
import org.madnews.service.TagService;
import org.madnews.service.UserService;
import org.madnews.utils.EmailResponseWrapper;
import org.madnews.utils.ResourceNotFoundException;
import org.madnews.utils.UsernameResponseWrapper;
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
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/news", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Post postNews(@RequestBody Post post){
        return postService.createPost(post);
    }
    
    @RequestMapping(value = "/tags", method = RequestMethod.POST)
    public Tag postTag(@RequestBody Tag tag){
        return tagService.createTag(tag);
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
    
    @RequestMapping(value = "/helpers/username-not-in-db/{username:^(?![_.])(?!.*[_.]{2})[A-Za-z0-9._]{3,15}$}", method = RequestMethod.GET)
    public ResponseEntity<UsernameResponseWrapper> isUserByUsername(@PathVariable String username){
    	boolean result = userService.hasUserByUsername(username);
    	UsernameResponseWrapper responseWrapper = new UsernameResponseWrapper(username, result);
    	return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
    }


    @RequestMapping(value="/image", method=RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Map<String,String>> handleFileUpload(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("src/main/webapp/media_files/"+file.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
                Map<String,String> imageLocation = new HashMap<String,String>();
                imageLocation.put("link", "media_files/" + file.getOriginalFilename());
                return new ResponseEntity<>(imageLocation, HttpStatus.OK);
            } catch (Exception e) {
            	Map<String,String> error = new HashMap<String,String>();
            	error.put("error", "You failed to upload => " + e.getMessage());
                return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        	Map<String,String> error = new HashMap<String,String>();
        	error.put("error", "You failed to upload because the file was empty.");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
}
