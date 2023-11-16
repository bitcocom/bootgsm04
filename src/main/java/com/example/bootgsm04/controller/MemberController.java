package com.example.bootgsm04.controller;

import com.example.bootgsm04.entity.Member;
import com.example.bootgsm04.service.MemberService;
import com.example.bootgsm04.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller  // 응답(JSON) : {     }
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    // http://localhost:9001/web/listView
    @GetMapping("/memberRegister")
    public String memberRegister(){
       return "member/register"; //register.html(회원가입 UI)
    }
    @PostMapping("/memberRegister")
    public String memberRegister(Member vo){ // 파라메터수집
        memberService.memberRegister(vo);//저장완료
        // 리스트페이지로 이동
        return "redirect:/member/listView";
    }
    @RequestMapping("/listView")
    public String listView(Model model){
        List<Member> lists=memberService.getMembers();
        // 객체바인딩
        model.addAttribute("lists", lists); // ${lists}
        return "member/list"; //list.html(Thymeleaf)
    }
    @GetMapping("/memberDelete/{id}")
    public String memberDelete(@PathVariable Long id){
        memberService.memberDelete(id);
        return "redirect:/member/listView";
    }
    @GetMapping("/memberDelete1/{memId}")
    public String memberDelete(@PathVariable String memId){
        memberService.memberDelete(memId);
        return "redirect:/member/listView";
    }
    @GetMapping("/memberDelete2/{memEmail}")
    public String memberDelete2(@PathVariable String memEmail){
        memberService.memberDelete2(memEmail);
        return "redirect:/member/listView";
    }
    @GetMapping("/view/{id}") // view/12
    public String view(@PathVariable Long id, Model model){
        Member member=memberService.memberDetail(id);
        model.addAttribute("member",member);
        // Controller ---forward--> View(JSP,HTML)
        return "member/view"; // view.html <- ${member.?}
    }
    @GetMapping("/modify/{id}")
    public String modify(@PathVariable Long id, Model model){
        Member member=memberService.memberDetail(id);
        model.addAttribute("member", member);
        return "member/modify"; // modify.html(수정화면)
    }
    @PostMapping("/modify")
    public String modify(Member m){ // id, memName, memAge, memEmail
        memberService.memberModify(m);//수정
        //return "redirect:/listView";
        return "redirect:/member/view/"+m.getId();
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin")
    //@EnableGlobalMethodSecurity(prePostEnabled = true)
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String admin(){
        return "member/admin"; //admin.html
    }


}