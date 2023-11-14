package com.example.bootgsm04.entity;

public interface OAuth2MemberInfo {
    public String getProviderId(); //공급자 아이디 ex) google, facebook
    public String getProvider(); //공급자 ex) google, facebook
    public String getName(); //사용자 이름 ex) 홍길동
    public String getEmail(); //사용자 이메일 ex) gildong@gmail.com
}
