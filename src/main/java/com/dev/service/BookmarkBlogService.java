package com.dev.service;

import com.dev.model.Blog;
import com.dev.model.Bookmark;
import com.dev.model.BookmarkBlog;
import com.dev.model.BookmarkBlogRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookmarkBlogService {
    private final BookmarkBlogRepository bookmarkBlogRepository;

    public BookmarkBlogService(BookmarkBlogRepository bookmarkBlogRepository) {
        this.bookmarkBlogRepository = bookmarkBlogRepository;
    }

    public BookmarkBlog create(int userId, int bookmarkId, int blogId) {
        BookmarkBlog newBookmarkBlog = new BookmarkBlog();
        newBookmarkBlog.setUserId(userId);
        newBookmarkBlog.setBookmarkId(bookmarkId);
        newBookmarkBlog.setBlogId(blogId);
        return bookmarkBlogRepository.save(newBookmarkBlog);
    }

    public void deleteById(int bookmarkBlogId) {
        bookmarkBlogRepository.deleteById(bookmarkBlogId);
    }

    public List<BookmarkBlog> listByBookmarkId(int bookmarkId) {
        Bookmark bookmark = new Bookmark();
        bookmark.setId(bookmarkId);
        return bookmarkBlogRepository.findAllByBookmark(bookmark);
    }
}
