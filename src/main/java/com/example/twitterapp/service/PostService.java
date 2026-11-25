package com.example.twitterapp.service;

import com.example.twitterapp.model.Post;
import com.example.twitterapp.repository.PostRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {
    private final PostRepository posts;
    public PostService(PostRepository posts) { this.posts = posts; }
    public List<Post> findAll() { return posts.findAll(Sort.by(Sort.Direction.DESC, "createdAt")); }
    public Post save(Post post) { return posts.save(post); }
}
