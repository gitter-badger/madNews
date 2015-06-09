package org.madnews.service.impl;

import org.madnews.entity.Post;
import org.madnews.repository.PostRepository;
import org.madnews.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post readPost(Long id) {
        return postRepository.findOne(id);
    }

    @Override
    public void updatePost(Post post) {
        Post postFromDB = postRepository.findOne(post.getId());
        postFromDB.setIsShowOnMain(post.isShowOnMain());
        postFromDB.setHtml(post.getHtml());
        postFromDB.setIsFeatured(post.isFeatured());
        postFromDB.setIsTopNews(post.isTopNews());
        postFromDB.setMainImg(post.getMainImg());
        postFromDB.setPosition(post.getPosition());
        postFromDB.setShortText(post.getShortText());
        //postFromDB.setTimestamp(new Timestamp(System.currentTimeMillis()));
        postFromDB.setTitle(post.getTitle());
        postFromDB.setTags(post.getTags());
        postRepository.save(postFromDB);
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
        return postRepository.findByIsTopNewsTrueAndTimestampGreaterThan(timestamp24hEarlier);
    }
    
    @Override
    public Page<Post> getPostsByTag(Long tagId, Pageable pageable) {
    	return postRepository.findAllByTagsIdOrderByTimestampDesc(tagId, pageable);
    }
    
    @Override
    public List<Post> readPostsOnMain() {
    	return postRepository.findByIsShowOnMainTrueOrderByTimestampDesc();
    }
}
