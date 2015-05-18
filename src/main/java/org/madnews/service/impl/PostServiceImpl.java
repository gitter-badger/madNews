package org.madnews.service.impl;

import org.madnews.entity.Post;
import org.madnews.repository.PostRepository;
import org.madnews.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public void createPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post readPost(Long id) {
        return postRepository.findOne(id);
    }

    @Override
    public void updatePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    @Override
    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getTodayTopNews() {
        Timestamp timestamp24hEarlier = new Timestamp(System.currentTimeMillis()-24*60*60*1000);
        return postRepository.findByIsTopNewsTrueAndLastChangedGreaterThan(timestamp24hEarlier);
    }
}
