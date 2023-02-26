package com.dev.model;

import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<Blog,Integer> {
    Blog findBlogById(int id);
}