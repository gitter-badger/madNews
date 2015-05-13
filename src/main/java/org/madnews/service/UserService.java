package org.madnews.service;

import org.madnews.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    User getUser(Long id);
    List getUsers();
    void updateUser(User user);
    void deleteUser(User user);
}
