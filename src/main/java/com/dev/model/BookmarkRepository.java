package com.dev.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookmarkRepository extends CrudRepository<Bookmark,Integer> {
    Bookmark findBookmarkById(int id);
    List<Bookmark> findAllByUserId(int userId);
}