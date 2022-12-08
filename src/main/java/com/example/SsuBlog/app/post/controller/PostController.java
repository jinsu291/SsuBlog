package com.example.SsuBlog.app.post.controller;

import com.example.SsuBlog.app.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController
{
    @Autowired
    private PostService postService;

    @RequestMapping("list")
    @ResponseBody
    public String showList() {
        postService.findById(1);

        return "HI";
    }
}
