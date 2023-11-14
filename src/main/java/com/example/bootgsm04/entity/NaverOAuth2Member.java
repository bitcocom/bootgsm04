package com.example.bootgsm04.entity;

import java.util.Map;

public class NaverOAuth2Member implements OAuth2MemberInfo {

    private Map<String,Object> attributes;
    public NaverOAuth2Member(Map<String,Object> attributes){
        this.attributes=attributes;
    }

    @Override
    public String getProviderId() {
        return (String)attributes.get("id");
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getEmail() {
        return (String)attributes.get("email");
    }

    @Override
    public String getName() {
        return (String)attributes.get("name");
    }
}
