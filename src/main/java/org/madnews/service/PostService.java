package org.madnews.service;

import org.madnews.entity.Post;

public interface PostService {
    void createPost(Post post);
    Post readPost(Long id);
    void updatePost(Post post);
    void deletePost(Post post);
    Iterable<Post> getPosts();
    Post getTodayTopNews();
}
