package org.madnews.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkController {

    @RequestMapping(value={"/"})
    public String getHomePage() {
        return "index";
    }

}
