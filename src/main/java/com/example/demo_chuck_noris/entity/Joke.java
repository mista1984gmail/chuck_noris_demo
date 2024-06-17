package com.example.demo_chuck_noris.entity;

import java.time.LocalDateTime;

public class Joke {

    private String joke;

    private LocalDateTime createdAt;

    public Joke(String joke, LocalDateTime createdAt) {
        this.joke = joke;
        this.createdAt = createdAt;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
