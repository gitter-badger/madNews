package org.madnews.service.impl;

import org.madnews.entity.Post;
import org.madnews.repository.PostRepository;
import org.madnews.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(Post post) {
    	preUpdate(post);
        return postRepository.save(post);
    }

    @Override
    public Post readPost(Long id) {
        return postRepository.findOne(id);
    }

    @Override
    public Post updatePost(Post post) {
        Post postFromDB = postRepository.findOne(post.getId());
        postFromDB.setIsShowOnMain(post.getIsShowOnMain());
        postFromDB.setHtml(post.getHtml());
        postFromDB.setIsFeatured(post.getIsFeatured());
        preUpdate(post);
        postFromDB.setIsTopNews(post.getIsTopNews());
        postFromDB.setMainImg(post.getMainImg());
        postFromDB.setPosition(post.getPosition());
        postFromDB.setShortText(post.getShortText());
        postFromDB.setTitle(post.getTitle());
        postFromDB.setTags(post.getTags());
        return postRepository.save(postFromDB);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.delete(id);
    }

    @Override
    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getTopNews() {
        return postRepository.findByIsTopNewsTrue();
    }
    
    @Override
    public Page<Post> getPostsByTag(Long tagId, Pageable pageable) {
    	return postRepository.findAllByTagsIdOrderByTimestampDesc(tagId, pageable);
    }
    
    @Override
    public List<Post> readPostsOnMain() {
    	return postRepository.findByIsShowOnMainTrueOrderByTimestampDesc();
    }
    
	@Override
	public Page<Post> readPostsNotShowOnMain(Pageable pageable) {
		return postRepository.findByIsShowOnMainFalseOrderByTimestampDesc(pageable);
	}
	
    void preUpdate(Post post) {
    	if (post.getIsTopNews()) {
	    	List<Post> posts = (List<Post>) postRepository.findAll();
	    	for (Post p : posts) {
	    		p.setIsTopNews(false);
	    	}
    	}
    }
}
