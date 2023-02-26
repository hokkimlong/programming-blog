package com.dev.controller;

import com.dev.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private final BlogService blogService;

    public HomeController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping(value="/")
    public String Home(Model model){
        model.addAttribute("blogs",blogService.list());
        model.addAttribute("title","Home");
        return "home";
    }

}
