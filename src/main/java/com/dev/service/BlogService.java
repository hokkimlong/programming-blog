package com.dev.service;

import com.dev.model.Blog;
import com.dev.model.BlogRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blog create(String title, String summary, String description) {
        Blog newBlog = new Blog();
        newBlog.setTitle(title);
        newBlog.setSummary(summary);
        newBlog.setDescription(description);
        return blogRepository.save(newBlog);
    }

    public Blog updateById(int blogId, String title, String summary, String description) {
        Blog updateBlog = this.getById(blogId);
        updateBlog.setTitle(title);
        updateBlog.setSummary(summary);
        updateBlog.setDescription(description);
        return blogRepository.save(updateBlog);
    }

    public void deleteById(int blogId) {
        blogRepository.deleteById(blogId);
    }

    public List<Blog> list() {
        return (List<Blog>) blogRepository.findAll();
    }

    public Blog getById(int blogId) {
        return blogRepository.findBlogById(blogId);
    }
}
