package org.madnews.service;

import org.madnews.entity.User;


public interface UserService {
    User addUser(User user);
    User getUser(Long id);
    Iterable<User> getUsers();
    void updateUser(User user);
    void deleteUser(User user);
    boolean hasUserByEmail(String email);
}
