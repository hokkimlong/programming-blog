package com.dev.controller;

import com.dev.model.Blog;
import com.dev.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping(value = "/blog/create")
    public String CreateBlogPage(Model model) {
        model.addAttribute("title", "Create Blog");
        return "blog-form";
    }

    @RequestMapping(value = "/blog/create", method = RequestMethod.POST)
    public String CreateBlog(@RequestParam String title, @RequestParam String summary, @RequestParam String description, Model model) {
        try {
            blogService.create(title, summary, description);
            return "redirect:/";
        } catch (Exception error) {
            model.addAttribute("message", error.getMessage());
            return "blog-form";
        }
    }

    @RequestMapping(value = "/blog/{id}")
    public String BlogDetail(@PathVariable(value = "id") int id, Model model) {
        Blog blog = blogService.getById(id);
        if (blog == null) {
            return "404";
        } else {
            model.addAttribute("blog", blog);
        }
        return "blog-detail";
    }

    @RequestMapping(value = "/blog/{id}/edit")
    public String EditBlogPage(@PathVariable(value = "id") int id, Model model) {
        Blog blog = blogService.getById(id);
        if (blog == null) {
            return "404";
        } else {
            model.addAttribute("blog", blog);
        }
        return "blog-form";
    }

    @RequestMapping(value = "/blog/{id}/edit", method = RequestMethod.POST)
    public String EditBlog(@PathVariable(value = "id") int id,
                           @RequestParam String title,
                           @RequestParam String summary,
                           @RequestParam String description,
                           Model model) {
        try {
            blogService.updateById(id, title, summary, description);
            return "redirect:/blog/" + id;
        } catch (Exception error) {
            model.addAttribute("message", error.getMessage());
            return "blog-form";
        }
    }

    @RequestMapping(value = "/blog/{id}/delete")
    public String DeleteBlog(@PathVariable(value = "id") int id) {
        try {
            blogService.deleteById(id);
            return "redirect:/";
        } catch (Exception error) {
            return "redirect:/";
        }
    }
}
