package com.example.bootgsm04.service;

import com.example.bootgsm04.entity.CustomMember;
import com.example.bootgsm04.entity.Member;
import com.example.bootgsm04.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service                                       // loadUserByUsername()
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;
    //  class User implements UserDetails
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       //  회원정보+게시글정보(List)+권한정보(Set)
        Optional<Member> optional = memberRepository.findByUsername(username);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        //return new User(member.getUsername(), member.getPassword(), Collections.emptyList());
        Member member=optional.get();
        /*CustomMember customMember=new CustomMember(
                member,
                member.getUsername(),
                member.getPassword(),
                Collections.emptyList()
                );*/
        //return new CustomUserDetails(member);
        //           UserDetails
        //              User
        //               |
        return  new CustomMember(member);// ->SpringSecutityHolder(세션으로 만들어진다)
        //                              [ CustomMember(?) -> authorization.principal. ]
        //                                    User        -> authorization.name
        //                                                -> authorization.password(X)
        //                                                -> authorization.authorities
        // ---------SecurityContextHolder------------
        //  JSESSIONID : 123456                     |                 (View:JSP,thymeleaf)
        //  Authentication Object -------------|    | -------------> #authentication.principal
        //    (UserDetails-implements->User-extends->CustomMember)     |
        //__________________________________________|LoginMember     #authentication.name
                                                   //   |            #authentication.authorities
    }
}
