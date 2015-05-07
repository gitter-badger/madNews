package org.madnews.dao;

import org.madnews.entity.User;

import java.util.List;

public interface UserDAO extends GenericDAO<User, Long> {
    List getUsers();
}
