package org.madnews.service;

import org.madnews.entity.User;


public interface UserService {
    User createUser(User user);
    User readUser(Long id);
    Iterable<User> readUsers();
    void updateUser(User user);
    void deleteUser(User user);
    boolean hasUserByEmail(String email);
    boolean hasUserByUsername(String username);
    String encrypt(String pass);
}
