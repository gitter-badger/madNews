package org.madnews.service.impl;

import org.madnews.entity.User;
import org.madnews.repository.UserRepository;
import org.madnews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(User user) {
        User userFromDB = userRepository.findOne(user.getId());
        userFromDB.setUsername(user.getUsername());
        userFromDB.setEmail(user.getEmail());
        userFromDB.setPassword(user.getPassword());
        userFromDB.setPermissions(user.getPermissions());
        userFromDB.setPosts(user.getPosts());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
    
	@Override
	public boolean hasUserByEmail(String email) {
        return userRepository.findByEmail(email).size() != 0;
	}

}
