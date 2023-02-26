package com.dev.model;

import jakarta.persistence.*;

@Entity
public class BookmarkBlog {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="blog_id")
    private Blog blog;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="bookmark_id")
    private Bookmark bookmark;

    public void setBookmarkId(int bookmarkId) {
        Bookmark bookmark = new Bookmark();
        bookmark.setId(bookmarkId);
        this.bookmark = bookmark;
    }

    public void setBlogId(int blogId){
        Blog blog = new Blog();
        blog.setId(blogId);
        this.blog = blog;
    }

    public void setUserId(int userId){
        User user = new User();
        user.setId(userId);
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bookmark getBookmark() {
        return bookmark;
    }

    public void setBookmark(Bookmark bookmark) {
        this.bookmark = bookmark;
    }
}
