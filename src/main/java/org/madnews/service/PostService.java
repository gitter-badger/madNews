package org.madnews.service;

import java.util.List;

import org.madnews.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Post createPost(Post post);
    Post readPost(Long id);
    Post updatePost(Post post);
    void deletePost(Long id);
    Iterable<Post> getPosts();
    Post getTodayTopNews();
	Page<Post> getPostsByTag(Long tagId, Pageable pageable);
	List<Post> readPostsOnMain();
	Page<Post> readPostsNotShowOnMain(Pageable pageable);
}
