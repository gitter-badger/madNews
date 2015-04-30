package org.madnews.dao;

import org.madnews.entity.Post;

import java.util.List;

public interface PostDAO {
    void addPost(Post post);
    List<Post> getPosts();
}
