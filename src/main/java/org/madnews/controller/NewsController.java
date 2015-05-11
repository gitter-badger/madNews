package org.madnews.controller;

import org.madnews.entity.Post;
import org.madnews.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/public")
public class NewsController {

    @Autowired
    private PostService postService;

    @RequestMapping(value ="/news/{id}")
    public Post getPost(@PathVariable Long id){
        return postService.readPost(id);
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public List getNews(){
        return postService.getPosts();
    }
}
