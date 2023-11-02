package com.example.bootgsm04.controller;

import com.example.bootgsm04.entity.Post;
import com.example.bootgsm04.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController{
    @Autowired
    private PostService postService;
    @GetMapping("/postWrite/{id}")
    public String postWrite(@PathVariable Long id, Model model){
        model.addAttribute("memberId", id);
        return "post/write"; // write.html
    }
    @PostMapping("/postWrite")
    public String postWrite(Post post, Long memberId){
        postService.postRegister(post, memberId);
        return "redirect:/member/listView";
    }

}
