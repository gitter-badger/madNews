package org.madnews.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonView;

import org.madnews.entity.Post;
import org.madnews.entity.Tag;
import org.madnews.entity.User;
import org.madnews.service.PermissionService;
import org.madnews.service.PostService;
import org.madnews.service.TagService;
import org.madnews.service.UserService;
import org.madnews.utils.EmailResponseWrapper;
import org.madnews.utils.GetUser;
import org.madnews.utils.ResourceNotFoundException;
import org.madnews.utils.UsernameResponseWrapper;
import org.madnews.utils.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value="/api/v1/private")
public class PrivateController extends GetUser{

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;
    @Autowired
    private PermissionService permissionService;

    @JsonView(View.SimplePost.class)
    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public Iterable<?> getNews(){
        List<Post> posts = (List<Post>) postService.getPosts();
        if (posts.size()==0){
            throw new ResourceNotFoundException();
        }
        return posts;
    }

    @JsonView(View.EditablePost.class)
    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public ResponseEntity<Post> postNews(@RequestBody Post post){
    	User user = userService.readUserByUsername(GettingUser());
    	if (user.getPermissions().contains(permissionService.readPermissionByName("news-add-edit-delete-own"))) {
			post.setUser(user);
			return new ResponseEntity<>(postService.createPost(post), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(post, HttpStatus.FORBIDDEN);
		}
    }
    
    @JsonView(View.EditablePost.class)
    @RequestMapping(value = "/news", method = RequestMethod.PUT)
	public ResponseEntity<Post> updateNews(@RequestBody Post post) {
    	User user = userService.readUserByUsername(GettingUser());
    	if (user.getPermissions().contains(permissionService.readPermissionByName("news-edit-any"))
    			|| user.getPermissions().contains(permissionService.readPermissionByName("news-edit-delete-any "))) {
			return new ResponseEntity<>(postService.updatePost(post), HttpStatus.OK);
		} else if (user.getPermissions().contains(permissionService.readPermissionByName("news-add-edit-delete-own"))) {
			if (user.getUsername().equals(post.getUser().getUsername())) {
			return new ResponseEntity<>(postService.updatePost(post), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(post, HttpStatus.FORBIDDEN);
			}
    	} else {
			return new ResponseEntity<>(post, HttpStatus.FORBIDDEN);
		}
	}	
	@RequestMapping(value = "/news/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteNews(@PathVariable("id") Long id) {
		User user = userService.readUserByUsername(GettingUser());
    	if (user.getPermissions().contains(permissionService.readPermissionByName("news-edit-delete-any "))) {
    		postService.deletePost(id);
    		return new ResponseEntity<>(HttpStatus.OK);
		} else if (user.getPermissions().contains(permissionService.readPermissionByName("news-add-edit-delete-own"))) {
			Post post = postService.readPost(id);
			if (user.getUsername().equals(post.getUser().getUsername())) {
				postService.deletePost(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
    	} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
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
    public ResponseEntity<User> postUser(@RequestBody User user){
    	User userLogin = userService.readUserByUsername(GettingUser());
    	if (userLogin.getPermissions().contains(permissionService.readPermissionByName("user-management"))) {
			return new ResponseEntity<User>(userService.createUser(user), HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.FORBIDDEN);
		}
    }
     
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user) {
    	User userLogin = userService.readUserByUsername(GettingUser());
    	if (userLogin.getPermissions().contains(permissionService.readPermissionByName("user-management"))) {
			return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteUser(@PathVariable("id") Long id) {
    	User userLogin = userService.readUserByUsername(GettingUser());
    	if (userLogin.getPermissions().contains(permissionService.readPermissionByName("user-management"))) {
    		userService.deleteUser(id);
    		return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
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
    ResponseEntity<Map<String,String>> handleFileUpload(@RequestParam("upload") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("src/main/webapp/media_files/"+file.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
                Map<String,String> imageLocation = new HashMap<>();
                imageLocation.put("link", "media_files/" + file.getOriginalFilename());
                return new ResponseEntity<>(imageLocation, HttpStatus.OK);
            } catch (Exception e) {
            	Map<String,String> error = new HashMap<>();
            	error.put("error", "You failed to upload => " + e.getMessage());
                return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        	Map<String,String> error = new HashMap<>();
        	error.put("error", "You failed to upload because the file was empty.");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/users/{id}/password/{newPassword}/{oldPassword}", method = RequestMethod.PUT)
    public ResponseEntity<User> updatePassword(@PathVariable("id") Long id,
                                               @PathVariable("newPassword") String newPassword,
                                               @PathVariable("oldPassword") String oldPassword) {
        User user = userService.readUser(id);
        User userLogin = userService.readUserByUsername(GettingUser());
        if (new BCryptPasswordEncoder().matches(oldPassword,user.getPassword())
        		|| userLogin.getPermissions().contains(permissionService.readPermissionByName("user-management"))) {
			user.setPassword(newPassword);
			return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
        } else {
        	return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
        }
    }
}
