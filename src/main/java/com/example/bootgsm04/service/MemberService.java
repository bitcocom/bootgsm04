package com.example.bootgsm04.service;

import com.example.bootgsm04.entity.Member;
import com.example.bootgsm04.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface MemberService {
    public Member memberRegister(Member vo);
    public List<Member> getMembers();
    public void memberDelete(Long id);
    @Transactional
    public void memberDelete(String memId);
    @Transactional
    public void memberDelete2(String memEmail);
    public Member memberDetail(Long id);
    public void memberModify(Member m);

    Optional<Member> getMember(String usernmame);
}

