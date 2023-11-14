package com.example.bootgsm04.service;

import com.example.bootgsm04.entity.Member;
import com.example.bootgsm04.entity.Role;
import com.example.bootgsm04.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service // new MemberServiceImpl()
public class MemberServiceImpl implements MemberService{
    @Autowired // DI
    private MemberRepository memberRepository; // EntityManagerFactory
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    public Member memberRegister(Member vo){ // 12345 -> $21A%^^SDQDDSroifoeiroerie
        // 1. 패스워드 해싱
        String hashedPassword = passwordEncoder.encode(vo.getPassword());
        vo.setPassword(hashedPassword);
        // 2. 권한 부여(ㅁUSER ㅁMANAGER ㅁADMIN)
        Set<Role> roles = new HashSet<>();
        Role userRole = roleService.findByName("USER"); // Find or create the USER role
        roles.add(userRole); // USER
        //roles.add(userRole); // MANAGER
        //roles.add(userRole); // ADMIN
        vo.setRoles(roles);
        // 비즈니스 로직 구현~~~
        return memberRepository.save(vo);//insert into member ~~
    }
    public List<Member> getMembers(){
        return memberRepository.findAll(Sort.by(Sort.Direction.ASC, "username"));// select * from member
    }
    public void memberDelete(Long id){
        memberRepository.deleteById(id); // delete from member where id=?
    }
    @Transactional
    public void memberDelete(String memId){
        memberRepository.deleteByUsername(memId);
    }
    @Transactional
    public void memberDelete2(String memEmail){
        memberRepository.deleteMemberByEmail(memEmail);
    }
    public Member memberDetail(Long id){
        Optional<Member> member=memberRepository.findById(id);
        return member.orElse(null);
    }
    public void memberModify(Member m){ // 수정데이터
        Optional<Member> optional=memberRepository.findById(m.getId());
        if(optional.isPresent()) {
            Member member=optional.get();
            member.setMemName(m.getMemName());
            member.setMemAge(m.getMemAge());
            member.setMemEmail(m.getMemEmail());
            memberRepository.save(member);
            // update member set mem_name=?,mem_age=?, mem_email=? where id=?
        }
    }
    @Override
    public Optional<Member> getMember(String username) {
        return memberRepository.findByUsername(username);
    }
}
