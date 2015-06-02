package org.madnews.repository;

import java.util.List;

import org.madnews.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findByEmail(String email);
}
