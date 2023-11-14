package com.example.bootgsm04.entity;

public interface OAuth2MemberInfo {
    public String getProviderId(); //공급자 아이디 ex) 10022000, 111000AAsss00
    public String getProvider(); //공급자 ex) google, facebook, naver
    public String getName(); //사용자 이름 ex) 홍길동
    public String getEmail(); //사용자 이메일 ex) gildong@gmail.com
}
