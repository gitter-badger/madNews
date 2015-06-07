package org.madnews.controller;

import com.fasterxml.jackson.annotation.JsonView;

import org.madnews.entity.Post;
import org.madnews.entity.Tag;
import org.madnews.service.PostService;
import org.madnews.service.TagService;
import org.madnews.utils.ResourceNotFoundException;
import org.madnews.utils.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/public")
public class PublicController {

    @Autowired
    private PostService postService;
    @Autowired
    private TagService tagService;

    @RequestMapping(value ="/news/{id}", method = RequestMethod.GET)
    @JsonView(View.FullPost.class)
    public Post getPost(@PathVariable Long id){
        Post post = postService.readPost(id);
        if (post==null){
            throw new ResourceNotFoundException();
        }
        return post;
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    @JsonView(View.ShortPost.class)
    public Iterable<?> getNews(){
        List posts = (List) postService.getPosts();
        if (posts.size()==0){
            throw new ResourceNotFoundException();
        }
        return posts;
    }

    @RequestMapping(value = "/news/tag/{id}", method = RequestMethod.GET)
    @JsonView(View.ShortPost.class)
    public ResponseEntity<PagedResources<Post>>  getPostsByTagId(@PathVariable Long id,
    		@PageableDefault(page = 0, size = 10) Pageable pageable,
    		PagedResourcesAssembler assembler){
    	Page<Post> posts = postService.getPostsByTag(id, pageable);
        return new ResponseEntity<PagedResources<Post>>(assembler.toResource(posts), HttpStatus.OK);
    }

    @RequestMapping(value = "/news/top", method = RequestMethod.GET)
    public Post getTodayTopNews(){
        Post post = postService.getTodayTopNews();
        if (post==null){
            throw new ResourceNotFoundException();
        }
        return post;
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public List getTags() {
        List tags = (List) tagService.getTags();
        if (tags.size()==0){
            throw new ResourceNotFoundException();
        }
        return tags;
    }
    
    @RequestMapping(value ="/tags/{id}", method = RequestMethod.GET)
    public Tag getTag(@PathVariable Long id){
        Tag tag = tagService.readTag(id);
        if (tag==null){
            throw new ResourceNotFoundException();
        }
        return tag;
    }
}