package org.madnews.dao;

import org.madnews.entity.Post;

import java.util.List;

public interface PostDAO extends GenericDAO<Post, Long>{
    List readPosts();
}
