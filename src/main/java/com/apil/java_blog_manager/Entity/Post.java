package com.apil.java_blog_manager.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private String content;
    private Long userid;

    public Post(String title, String content, Long userid) {
        this.title = title;
        this.content = content;
        this.userid = userid;
    }

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long user_id) {
        this.userid = user_id;
    }
}
