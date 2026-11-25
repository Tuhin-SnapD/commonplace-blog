package com.example.twitterapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "blog_posts")
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 180)
    private String title;
    @Column(nullable = false, length = 10000)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
