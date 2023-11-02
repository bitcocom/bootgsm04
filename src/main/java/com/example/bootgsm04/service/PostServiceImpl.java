package com.example.bootgsm04.service;

import com.example.bootgsm04.entity.Member;
import com.example.bootgsm04.entity.Post;
import com.example.bootgsm04.repository.MemberRepository;
import com.example.bootgsm04.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public Post postRegister(Post post, Long memberId) {
        Optional<Member> optional=memberRepository.findById(memberId);
        post.setMember(optional.get()); // member_id = id
        return postRepository.save(post); // insert SQL ~
    }
}
