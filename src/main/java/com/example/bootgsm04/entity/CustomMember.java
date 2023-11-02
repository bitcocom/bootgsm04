package com.example.bootgsm04.entity;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

public class CustomMember extends User implements OAuth2User {
    private Member member;
    private Map<String, Object> attributes;

    //private String memName;
    public CustomMember(Member member) { //               USER, MANAGER, ADMIN
        super(member.getUsername(), member.getPassword(), getAuthorities(member.getRoles())); // User클래스에 넘겨 준다.
        this.member=member;
        //memName=member.getMemName();
    } // User implements UserDetails
   /* private static List<GrantedAuthority> getAuthorities(Set<Role> roles){
        List<GrantedAuthority> list=new ArrayList<>();
        for(Role role : roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
            //list.add(new SimpleGrantedAuthority(role.getName()));
        }
        return list;
    }*/
   public CustomMember(Member member, Map<String, Object> attributes) {
       super(member.getUsername(), member.getPassword(), Collections.emptyList()); // User클래스에 넘겨 준다.
       this.member=member;
       this.attributes=attributes;
   }
    private static Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
           return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList()); // [ROLE_USER, ROLE_MANAGER, ROLE_ADMIN]
    }
    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return null;
    }
}
/*
   - UserDetails(interface) : 사용자의 세부정보를 가리키는 객체
   + 아이디 정보를 얻는 메서드 : getUsername()
   + 패스워드 정보를 얻는 메서드 : getPassword()
   + 권한 정보를 얻는 메서드 : getAuthorities() =>
   + 1) implements -> User class(아이디,패스워드,권한정보) -> 세션에 저장
   + 2) implements -> CustomMember class : 비추(X)
   + 3) extends User ->  CustomMember class : 추전(O) -> 세선에 저장
                         CustomMember = User + [이메일, 주소 , 나이..]
                                                    Member






 */