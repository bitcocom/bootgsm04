package com.example.bootgsm04.config;

import com.example.bootgsm04.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Controller;

@Configuration  //의미 + 객체생성(new)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter { //abstract

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {  // 회원가입 시 비밀번호 암호화에 사용할 Encoder 빈 등록
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()// 1. 요청 URL에 따라 -> 인증과 권한을 설정
               // .antMatchers("/member/admin").hasAuthority("ROLE_ADMIN")
                .antMatchers("/post/**").authenticated() //해당 경로는 로그인 권한이 있어야 함
                //.antMatchers("/admin/**").hasRole("ROLE_ADMIN")
                // 관리자 버튼
                // 매니저 버튼
                // 일반 사용자
                .anyRequest().permitAll() //모든 경로 허용
                .and()
                .formLogin() //2. 로그인 폼에 대한 설정 : /login -> 내부 로그인 UI
                // /member/listView -> 개발자가 새로 정의한 로그인 UI(list.html)
                    .loginPage("/member/listView") // @Controller
                     // @RequestMapping("/listView");
                     // return "login"; login.html(새로 만들기)
                    .loginProcessingUrl("/login") // POST-->SpringSecurity 동작(UsernamePasswordAuthenticationFilter)
                    .defaultSuccessUrl("/member/listView") // 고정값으로 설정
                    .and()
                .logout()
                    .logoutUrl("/logout") // POST(form: action), GET(a:href)
                    .logoutSuccessUrl("/member/listView")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // GET 요청으로도 로그아웃 가능하도록 설정
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                .and()
                .oauth2Login()
                .loginPage("/member/listView")
                .defaultSuccessUrl("/member/listView");

    }

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }*/
}

