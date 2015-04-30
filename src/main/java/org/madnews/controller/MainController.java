package org.madnews.controller;

import org.madnews.dao.PostDAO;
import org.madnews.dao.UserDAO;
import org.madnews.dao.impl.PostDAOImpl;
import org.madnews.dao.impl.UserDAOImpl;
import org.madnews.entity.Post;
import org.madnews.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value="/api/v1/public/")
public class MainController {
    private static PostDAO postDAO = new PostDAOImpl();

    @RequestMapping(value = "api/v1/public/posts", method = RequestMethod.GET)
    @ResponseBody
    public String getNews(Model model){
        List<Post> posts = postDAO.getPosts();
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
