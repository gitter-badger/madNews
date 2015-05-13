package org.madnews.service;

import org.madnews.entity.Post;
import java.util.List;

public interface PostService {
    void createPost(Post post);
    Post readPost(Long id);
    void updatePost(Post post);
    void deletePost(Post post);
    List getPosts();
    Post getTodayTopNews();
}
