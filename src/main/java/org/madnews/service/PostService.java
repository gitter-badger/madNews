package org.madnews.service;

import org.madnews.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Post createPost(Post post);
    Post readPost(Long id);
    void updatePost(Post post);
    void deletePost(Post post);
    Iterable<Post> getPosts();
    Post getTodayTopNews();
	Page<Post> getPostsByTag(Long tagId, Pageable pageable);
}
