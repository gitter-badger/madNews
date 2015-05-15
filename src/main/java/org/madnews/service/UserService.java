package org.madnews.service;

import org.madnews.entity.User;


public interface UserService {
    void addUser(User user);
    User getUser(Long id);
    Iterable<User> getUsers();
    void updateUser(User user);
    void deleteUser(User user);
}
