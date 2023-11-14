package com.example.bootgsm04.service;

import com.example.bootgsm04.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service                                           // OAuth2UserService interface
public class CustomOAuth2UserDetailsService extends DefaultOAuth2UserService {
    @Autowired
    private MemberService memberService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    @Override       // google, facebook <---> naver, kakao
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 회원가입?
        System.out.println("1.getClientRegistration:" + userRequest.getClientRegistration());
        System.out.println("2.getAccessToken:" + userRequest.getAccessToken().getTokenValue());
        // 이부분에서 사용자 정보 객체를 얻어온다(OAuth2User)
        OAuth2User oauth2User = super.loadUser(userRequest);
        System.out.println("3.getAttributes:" + oauth2User.getAttributes()); // <사용자정보>
        // Map<String, Object>
        // OAuth2User -> CustomMember
        // google(https://console.cloud.google.com/), facebook <---> naver, kakao
        // Google, Facebook, Naver, Kakao ~
        OAuth2MemberInfo oAuth2MemberInfo=null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            System.out.println("구글 로그인 요청");
            oAuth2MemberInfo=new GoogleOAuth2Member(oauth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")){
            System.out.println("페이스북 로그인 요청");
            //oAuth2UserInfo=new FacebookUserInfo(oauth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")){
            System.out.println("네이버 로그인 요청");
            oAuth2MemberInfo=new NaverOAuth2Member((Map)oauth2User.getAttributes().get("response"));
        }else{
            System.out.println("우리는 구글과 페이스북과 네이버만 지원해요 ㅎㅎ");
        }
        String provider=oAuth2MemberInfo.getProvider();//google, naver
        String providerId=oAuth2MemberInfo.getProviderId();
        String username=provider+"_"+providerId;
        // ?
        String password=passwordEncoder.encode("겟인테어");
        //String password="겟인테어";
        String memEmail=oAuth2MemberInfo.getEmail();
        String memName=oAuth2MemberInfo.getName();
        //String id=userRequest.getClientRegistration().getRegistrationId();
        //String usernmame= id+"_"+(String) oauth2User.getAttributes().get("sub");
        //String password=passwordEncoder.encode("겟인테어");
        //String memName= (String) oauth2User.getAttributes().get("name");
        //String memEmail= (String) oauth2User.getAttributes().get("email");

        //Member member=new Member();
        Set<Role> roles = new HashSet<>();
        Role userRole = roleService.findByName("USER"); // Find or create the USER role
        roles.add(userRole);
        // 회원등록 여부 체크
        Optional<Member> optional=memberService.getMember(username);
        Member member=null;
        if(optional.isPresent()){
            System.out.println("로그인을 이미 한적이 있습니다.당신은 자동회원가입이 되어있습니다.");
            member=optional.get();
        }else{
            System.out.println("처음 oAuth2 로그인 사용자 입니다.");
            member = new Member();
            member.setUsername(username);
            member.setPassword(password);
            member.setMemName(memName);
            member.setMemEmail(memEmail);
            member.setMemAge(0); // 수정
            member.setRoles(roles); //USER
            memberService.oAuth2memberRegister(member); // 강제로 회원가입
        }
        return new CustomMember(member);
    }
}
/*
1. registrationId='google'
3. getAttributes:
{
  sub=101926511570168785716,  --> google_101926511570168785716 : username
                              --> 임의의패스워드(암호화) : password
  name=박매일,                 --> 박매일 : memName
  given_name=매일,             --> 나이 : 입력받기(X)
  family_name=박,              --> 전화번호 : 입력받기(X)
  picture=https://lh3.googleusercontent.com/a/ACg8ocLAJv5FpgHCGZ81L3NnX613GjgnQCvUTpT1GjsEooVB=s96-c,
  email=bmy19751975@gmail.com, --> bmy19751975@gmail.com : memEmail
  email_verified=true,         --> role : USER
  locale=ko
  }
 */