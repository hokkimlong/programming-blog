package com.dev.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookmarkBlogRepository extends CrudRepository<BookmarkBlog,Integer> {
    List<BookmarkBlog> findAllByBookmark(Bookmark bookmark);
}