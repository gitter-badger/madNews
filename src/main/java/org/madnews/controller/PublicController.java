package org.madnews.controller;

import org.madnews.entity.Post;
import org.madnews.entity.Tag;
import org.madnews.service.PostService;
import org.madnews.service.TagService;
import org.madnews.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    //@JsonView(View.SimplePost.class)
    @RequestMapping(value = "/news/top", method = RequestMethod.GET)
    public Post getTopNews(){
        Post post = postService.getTopNews();
        if (post==null){
            throw new ResourceNotFoundException();
        }
        return post;
    }

    //@JsonView(View.FullPost.class)
    @RequestMapping(value ="/news/{id}", method = RequestMethod.GET)
    public Post getPost(@PathVariable Long id){
        Post post = postService.readPost(id);
        if (post==null){
            throw new ResourceNotFoundException();
        }
        return post;
    }

    //@JsonView(View.SimplePost.class)
    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public Iterable<?> getNews(){
        List<Post> posts = postService.readPostsOnMain();
        if (posts.size()==0){
            throw new ResourceNotFoundException();
        }
        return posts;
    }

    @RequestMapping(value = "/news/tag/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedResources<Post>>  getPostsByTagId(@PathVariable Long id,
    		@PageableDefault(page = 0, size = 25) Pageable pageable,
    		PagedResourcesAssembler assembler){
    	Page<Post> posts = postService.getPostsByTag(id, pageable);
        return new ResponseEntity<PagedResources<Post>>(assembler.toResource(posts), HttpStatus.OK);
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

   // @JsonView(View.SimplePost.class)
    @RequestMapping(value = "/archive", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedResources<Post>>  getArchive(Pageable pageable,
    		PagedResourcesAssembler assembler){
    	Page<Post> posts = postService.readPostsNotShowOnMain(pageable);
        return new ResponseEntity<PagedResources<Post>>(assembler.toResource(posts), HttpStatus.OK);
    }
}
