package org.madnews.service.impl;

import org.madnews.dao.UserDAO;
import org.madnews.entity.User;
import org.madnews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void addUser(User user) {
        userDAO.create(user);
    }

    @Override
    public User getUser(Long id) {
        return userDAO.read(id);
    }

    @Override
    public List getUsers() {
        return userDAO.getUsers();
    }

    @Override
    public void updateUser(User user) {
        userDAO.update(user);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.delete(user);
    }
}
