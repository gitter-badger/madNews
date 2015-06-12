package org.madnews.service.impl;

import org.madnews.entity.User;
import org.madnews.repository.UserRepository;
import org.madnews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        user.setPassword(encrypt(user.getPassword()));
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
		return userRepository.findByUsername(username).size() != 0;
	}

	@Override
    public String encrypt(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
