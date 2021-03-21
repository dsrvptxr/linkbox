package com.dino.blog;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long id) {
        super("Could not find customer " + id);
    }
}

