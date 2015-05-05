package org.madnews.controller;

import org.madnews.service.Service;
import org.madnews.entity.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value="/api/v1/public/")
public class MainController {
    @Resource(name = "service")
    private static Service service;

    @RequestMapping(value = "api/v1/public/posts", method = RequestMethod.GET)
    @ResponseBody
    public String getNews(Model model){
        List<Post> posts = service.getPosts();
        String json = "";
        String postJson;
        for(Post post: posts){
            postJson =
                "{" +
                "id: " + post.getId() +
                "title" + post.getTitle() +
                "timestamp" + post.getTimestamp() +
                "html" + post.getContent() +
                "tags" + "[1,2,3]" +
                "}";
            json.concat(postJson);
        }
        return json;
    }
}
