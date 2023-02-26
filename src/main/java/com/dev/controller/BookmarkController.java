package com.dev.controller;

import com.dev.model.Blog;
import com.dev.model.Bookmark;
import com.dev.model.BookmarkBlog;
import com.dev.model.User;
import com.dev.service.BlogService;
import com.dev.service.BookmarkBlogService;
import com.dev.service.BookmarkService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Book;
import java.util.List;

@Controller
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final BookmarkBlogService bookmarkBlogService;
    private  final BlogService blogService;

    public BookmarkController(BookmarkService bookmarkService, BookmarkBlogService bookmarkBlogService, BlogService blogService) {
        this.bookmarkService = bookmarkService;
        this.bookmarkBlogService = bookmarkBlogService;
        this.blogService = blogService;
    }

    @RequestMapping(value = "/bookmark")
    public String BookmarkPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("bookmarks", bookmarkService.listByUser(user.getId()));
        model.addAttribute("title", "Bookmark");
        return "bookmark";
    }

    @RequestMapping(value = "/bookmark/add-blog/{id}")
    public String AddToBookmarkPage(@PathVariable(value = "id") int id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("bookmarks", bookmarkService.listByUser(user.getId()));
        model.addAttribute("blog", blogService.getById(id));
        model.addAttribute("title", "Add Bookmark");
        return "add-to-bookmark";
    }

    @RequestMapping(value = "/bookmark/add-blog",method = RequestMethod.POST)
    public String AddToBookmarkPage(@RequestParam int blogId,@RequestParam int bookmarkId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        bookmarkBlogService.create(user.getId(),bookmarkId, blogId);
        return "redirect:/blog/"+blogId;
    }

    @RequestMapping(value = "/bookmark/create")
    public String CreateBookmarkPage(Model model) {
        model.addAttribute("title", "Create Bookmark");
        return "bookmark-form";
    }

    @RequestMapping(value = "/bookmark/create", method = RequestMethod.POST)
    public String CreateBookmark(@RequestParam String name, Model model, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            bookmarkService.create(name, user.getId());
            return "redirect:/bookmark";
        } catch (Exception error) {
            model.addAttribute("message", error.getMessage());
            return "bookmark-form";
        }
    }

    @RequestMapping(value = "/bookmark/{id}")
    public String BlogDetail(@PathVariable(value = "id") int id, Model model) {
        Bookmark bookmark = bookmarkService.getById(id);
        List<BookmarkBlog> bookmarkBlogs = bookmarkBlogService.listByBookmarkId(id);
        if (bookmark == null) {
            return "404";
        } else {
            model.addAttribute("bookmarkBlogs", bookmarkBlogs);
            model.addAttribute("bookmark", bookmark);
        }
        return "bookmark-list-blog";
    }


    @RequestMapping(value = "/bookmark/{id}/edit")
    public String EditBookmarkPage(@PathVariable(value = "id") int id, Model model) {
        Bookmark bookmark = bookmarkService.getById(id);
        if (bookmark == null) {
            return "404";
        } else {
            model.addAttribute("bookmark", bookmark);
        }
        return "bookmark-form";
    }

    @RequestMapping(value = "/bookmark/{id}/edit", method = RequestMethod.POST)
    public String EditBlog(@PathVariable(value = "id") int id,
                           @RequestParam String name,
                           Model model) {
        try {
            bookmarkService.updateById(id, name);
            return "redirect:/bookmark";
        } catch (Exception error) {
            model.addAttribute("message", error.getMessage());
            return "bookmark-form";
        }
    }

    @RequestMapping(value = "/bookmark/{id}/delete")
    public String DeleteBlog(@PathVariable(value = "id") int id) {
        try {
            bookmarkService.deleteById(id);
            return "redirect:/bookmark";
        } catch (Exception error) {
            return "redirect:/bookmark";
        }
    }

    @RequestMapping(value = "/bookmark-blog/{bookmarkBlogId}/{bookmarkId}/delete")
    public String DeleteBookmarkBlog(@PathVariable(value = "bookmarkBlogId") int bookmarkBlogId,@PathVariable (value="bookmarkId") int bookmarkId) {
        try {
            bookmarkBlogService.deleteById(bookmarkBlogId);
            return "redirect:/bookmark/"+bookmarkId;
        } catch (Exception error) {
            return "redirect:/bookmark/"+bookmarkId;
        }
    }

}
