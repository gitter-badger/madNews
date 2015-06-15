package org.madnews.service;

import org.madnews.entity.User;


public interface UserService {
    User createUser(User user);
    User readUser(Long id);
    Iterable<User> readUsers();
    User updateUser(User user);
    void deleteUser(Long id);
    boolean hasUserByEmail(String email);
    boolean hasUserByUsername(String username);
    User readUserByUsername(String username);
}