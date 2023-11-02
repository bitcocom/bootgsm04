package com.example.bootgsm04.service;

import com.example.bootgsm04.entity.*;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomOAuth2UserDetailsService extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("=================================");
        System.out.println(userRequest.toString());
        System.out.println("getClientRegistration:" + userRequest.getClientRegistration());
        System.out.println("getAccessToken:" + userRequest.getAccessToken().getTokenValue());

        OAuth2User oauth2User = super.loadUser(userRequest);
        System.out.println("getAttributes:" + oauth2User.getAttributes());
        Member newMember=new Member();
        newMember.setUsername(oauth2User.getAttribute("sub")); // 생성
        newMember.setMemName(oauth2User.getAttribute("name")); // 이름
        newMember.setPassword("XXXXX");
        return new CustomMember(newMember, Collections.emptyMap());
    }
}
