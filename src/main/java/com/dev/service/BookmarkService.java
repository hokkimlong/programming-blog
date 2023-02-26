package com.dev.service;

import com.dev.model.Blog;
import com.dev.model.Bookmark;
import com.dev.model.BookmarkRepository;
import com.dev.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }


    public Bookmark  create(String name,int userId) {
        Bookmark newBookmark = new Bookmark();
        newBookmark.setName(name);
        User createdUser = new User();
        createdUser.setId(userId);
        newBookmark.setUser(createdUser);
        return bookmarkRepository.save(newBookmark);
    }

    public Bookmark  updateById(int bookmarkId, String name) {
        Bookmark updateBookmark = this.getById(bookmarkId);
        updateBookmark.setName(name);
        return bookmarkRepository.save(updateBookmark);
    }

    public void deleteById(int blogId) {
        bookmarkRepository.deleteById(blogId);
    }

    public List<Bookmark> list() {
        return (List<Bookmark>) bookmarkRepository.findAll();
    }

    public List<Bookmark> listByUser(int userId){
        return (List<Bookmark>) bookmarkRepository.findAllByUserId(userId);
    }

    public Bookmark getById(int bookmarkId) {
        return bookmarkRepository.findBookmarkById(bookmarkId);
    }
}
