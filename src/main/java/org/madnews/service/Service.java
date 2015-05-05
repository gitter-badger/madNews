package org.madnews.service;

import org.madnews.entity.Post;
import org.madnews.entity.User;

import java.util.List;

public interface Service {
    void addPost(Post post);
    List<Post> getPosts();

    void addUser(User user);
    User getUser(Integer id);
    List<User> getUsers();
    void updateUser(User user);
    void deleteUser(Integer id);
}
