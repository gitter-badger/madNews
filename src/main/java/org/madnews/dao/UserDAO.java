package org.madnews.dao;

import org.madnews.entity.User;

import java.util.List;

/**
 * Created by andrey on 4/26/2015.
 */
public interface UserDAO {
    void addUser(User user);
    User getUser(Integer id);
    List<User> getUsers();
    void updateUser(User user);
    void deleteUser(Integer id);
}
