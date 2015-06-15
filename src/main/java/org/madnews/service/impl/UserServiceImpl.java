package org.madnews.service.impl;

import org.madnews.entity.User;
import org.madnews.repository.UserRepository;
import org.madnews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User readUser(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public Iterable<User> readUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User userFromDB = userRepository.findOne(user.getId());
        userFromDB.setUsername(user.getUsername());
        userFromDB.setEmail(user.getEmail());
        userFromDB.setPermissions(user.getPermissions());
        userFromDB.setPosts(user.getPosts());
        userFromDB.setEnabled(user.isEnabled());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }
    
	@Override
	public boolean hasUserByEmail(String email) {
        return userRepository.findByEmail(email).size() != 0;
	}

	@Override
	public boolean hasUserByUsername(String username) {
		return userRepository.findByUsername(username) != null;
	}
	
	@Override
	public User readUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
