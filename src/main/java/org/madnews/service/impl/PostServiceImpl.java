package org.madnews.service.impl;

import org.madnews.dao.PostDAO;
import org.madnews.entity.Post;
import org.madnews.entity.Tag;
import org.madnews.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    @Override
    public void createPost(Post post) {
        postDAO.create(post);
    }

    @Override
    public Post readPost(Long id) {
        return postDAO.read(id);
    }

    @Override
    public void updatePost(Post post) {
        postDAO.update(post);
    }

    @Override
    public void deletePost(Post post) {
        postDAO.delete(post);
    }

    @Override
    public List<Post> getPosts() {
        return postDAO.readPosts();
    }
}
