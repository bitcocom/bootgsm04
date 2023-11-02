package com.example.bootgsm04.service;

import com.example.bootgsm04.entity.Post;
import com.example.bootgsm04.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface PostService {
    public Post postRegister(Post post, Long memberId);

}
