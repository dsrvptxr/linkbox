package com.dino.blog.models;

import com.dino.blog.PostNotFoundException;
import com.dino.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

public class PostController {

    private final PostRepository repository;

    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/letters")
    List<Post> all() {
        return (List<Post>) repository.findAll();
    }

    @GetMapping("/letters/{id}")
    Post one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    }

    @PostMapping("/letters")
    Post newPost(@RequestBody Post newPst) {
        return repository.save(newPst);
    }

    @PutMapping("/letters/{id}")
    Post put(@RequestBody Post replacePost, @PathVariable Long id) {
        return repository.findById(id).map(post -> {
            post = replacePost;
            post.setId(id);
            return repository.save(post);
        }).orElseThrow(() -> new PostNotFoundException(id));
    }

    @DeleteMapping("/letters/{id}")
    void del(@PathVariable Long id) {
        repository.deleteById(id);
    }
}